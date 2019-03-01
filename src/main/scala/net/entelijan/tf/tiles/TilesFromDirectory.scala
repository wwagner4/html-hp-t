package net.entelijan.tf.tiles

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.{AlphaComposite, RenderingHints}
import java.io.File
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.name.Rename
import net.entelijan.tf.tiles.TilesFromDirectory.bufferedImages

import scala.collection.JavaConverters._

object TilesFromDirectory {

  def thumbnails(thumbSize: Size, indir: Path, outdir: Path): Unit = {
    if (!Files.exists(outdir)) {
      Files.createDirectories(outdir)
    }

    Thumbnails.of(imgFiles(indir): _*)
      .forceSize(thumbSize.width, thumbSize.height)
      .outputQuality(1.0)
      .toFiles(outdir.toFile, Rename.NO_CHANGE)
  }

  private def imgFiles(dir: Path): Array[File] =
    Files.list(dir)
      .filter(isImageFile)
      .iterator()
      .asScala
      .toArray
      .map(_.toFile)


  def isImageFile(p: Path): Boolean =
    Files.isRegularFile(p) &&
      p.getFileName.toString.toLowerCase().endsWith("jpg") ||
      p.getFileName.toString.toLowerCase().endsWith("jpeg") ||
      p.getFileName.toString.toLowerCase().endsWith("png")


  def bufferedImages(indir: Path): Seq[(String, BufferedImage)] = {
    def asBuffered(p: Path): BufferedImage = ImageIO.read(p.toFile)

    Files.list(indir)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toStream
      .map(p => (p.getFileName.toString, asBuffered(p)))
  }


  def tiles(name: String, cols: Int, tileSize: Size, indir: Path, outdir: Path): Unit = {
    require(Files.exists(indir), s"$indir must exist")
    require(Files.isDirectory(indir), s"$indir must be a directory")
    if (!Files.exists(outdir))
      Files.createDirectories(outdir)
    val bimages = bufferedImages(indir)

    val images = for ((id, bi) <- bimages) yield {
      Image(id = id, size = Size(bi.getWidth, bi.getHeight))
    }

    val bimagesMap = bimages.toMap

    val gr = Geometry.tiles(1200, 3)(images)
    val outImg = new BufferedImage(gr.width, gr.height, BufferedImage.TYPE_INT_RGB)
    val grOutImg = outImg.getGraphics
    gr.tiles.foreach { tile =>
      val src = bimagesMap(tile.id)
      val at = new AffineTransform
      at.scale(tile.scale.x, tile.scale.y)
      val grdest = src.createGraphics()
      grdest.setComposite(AlphaComposite.Src)
      grdest.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
      grdest.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
      grdest.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      grdest.transform(at)
      grOutImg.drawImage(src, tile.xoff, tile.yoff, gr.tileWidth, gr.tileHeight, null)
    }
    if (!Files.exists(outdir)) {
      Files.createDirectories(outdir)
    }
    val imgtype = "jpg"
    val outPath = outdir.resolve(s"$name.$imgtype")

    ImageIO.write(outImg, imgtype, outPath.toFile)

  }


}
