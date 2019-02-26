package net.entelijan.tf.tiles

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO
import java.awt.geom.AffineTransform
import java.awt.AlphaComposite
import java.awt.RenderingHints
import java.io.File
import java.util.stream

import net.coobird.thumbnailator.name.Rename
import net.coobird.thumbnailator.{Thumbnailator, Thumbnails}

import scala.collection.JavaConverters._

object TilesFromDirectory {

  def thumbnails(p: Path, destDir: Path): Unit = {
    if (!Files.exists(destDir)) {
      Files.createDirectories(destDir)
    }
    val files = Files.list(p)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toArray
      .map(p => p.toFile)

    Thumbnails.of(files:_*)
      .scale(0.3)
      .rotate(10.2)
      .toFiles(destDir.toFile, Rename.NO_CHANGE)
  }


  def isImageFile(p: Path): Boolean =
    Files.isRegularFile(p) &&
      p.getFileName.toString.toLowerCase().endsWith("jpg") ||
      p.getFileName.toString.toLowerCase().endsWith("jpeg") ||
      p.getFileName.toString.toLowerCase().endsWith("png")

  def asBuffered(p: Path): BufferedImage = ImageIO.read(p.toFile)

  def create(name: String, indir: Path, outdir: Path): Unit = {
    require(Files.exists(indir), s"$indir must exist")
    require(Files.isDirectory(indir), s"$indir must be a directory")
    if (!Files.exists(outdir))
      Files.createDirectories(outdir)
    val bimages: Seq[(String, BufferedImage)] = Files.list(indir)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toList
      .map(p => (p.getFileName.toString, asBuffered(p)))

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

    println("Wrote to " + outPath)
  }

}
