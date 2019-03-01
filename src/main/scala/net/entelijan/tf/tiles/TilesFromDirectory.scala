package net.entelijan.tf.tiles

import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.name.Rename

import scala.collection.JavaConverters._


object TilesFromDirectory {

  case class NamedImage(name: String, image: BufferedImage)

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


  def namedImages(indir: Path): Seq[NamedImage] = {

    def asBuffered(p: Path): BufferedImage = {
      ImageIO.read(p.toFile)
    }

    Files.list(indir)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toList
      .map(p => NamedImage(p.getFileName.toString, asBuffered(p)))
      .sortBy(a => a.name)
  }


  def squared(bi: BufferedImage): BufferedImage = {
    val w = bi.getWidth
    val h = bi.getHeight

    def portrait: BufferedImage = {
      val out = new BufferedImage(w, w, BufferedImage.TYPE_INT_RGB)
      val off = ((h - w).toDouble / 2).toInt
      bi.getSubimage(0, off, w, w)
    }

    def landscape: BufferedImage = {
      val out = new BufferedImage(h, h, BufferedImage.TYPE_INT_RGB)
      val off = math.ceil((w - h).toDouble / 2).toInt
      bi.getSubimage(off, 0, h, h)
    }

    if (w == h) bi
    else if (w < h) portrait
    else landscape
  }

  def thumb(thumbSize: Size)(ni: BufferedImage): BufferedImage = {
    Thumbnails.of(ni)
      .forceSize(thumbSize.width, thumbSize.height)
      .asBufferedImage()
  }

  def mapNamed(f: BufferedImage => BufferedImage)(ni: NamedImage): NamedImage = {
    NamedImage(ni.name, f(ni.image))
  }

  def tiles(name: String, cols: Int, size: Int, indir: Path, outdir: Path): Unit = {
    val tileSize = Size(size, size)
    require(Files.exists(indir), s"$indir must exist")
    require(Files.isDirectory(indir), s"$indir must be a directory")
    if (!Files.exists(outdir))
      Files.createDirectories(outdir)
    val images = namedImages(indir)
      .map(mapNamed(squared) _)
      .map(mapNamed(thumb(tileSize) _) _)

    val imgMap = images.map(i => (i.name, i.image)).toMap

    val names = images.map(is => is.name)
    val gr = Geometry.tiles(tileSize, cols)(names)
    val outImg = new BufferedImage(gr.width, gr.height, BufferedImage.TYPE_INT_RGB)
    val grOutImg = outImg.getGraphics
    gr.tiles.foreach { tile =>
      val src = imgMap(tile.id)
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
