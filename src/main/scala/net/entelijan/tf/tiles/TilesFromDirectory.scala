package net.entelijan.tf.tiles

import java.awt.image.BufferedImage
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO
import net.coobird.thumbnailator.Thumbnails

import scala.collection.JavaConverters._


object TilesFromDirectory {

  case class NamedBufferedImage(name: String, image: BufferedImage)

  def squaredTiles(name: String, cols: Int, tileSize: Int, indir: Path, outdir: Path): Unit = {
    val size = Size(tileSize, tileSize)
    require(Files.exists(indir), s"$indir must exist")
    require(Files.isDirectory(indir), s"$indir must be a directory")
    if (!Files.exists(outdir))
      Files.createDirectories(outdir)
    val images = dirToNamedBufferedImages(indir)
      .map(mapNamed(cropToSquare(10)))
      .map(mapNamed(scaleSquare(size)))

    val imgMap = images.map(i => (i.name, i.image)).toMap

    val names = images.map(is => is.name)
    val gr = Geometry.tiles(size, cols)(names)
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


  private def dirToNamedBufferedImages(indir: Path): Seq[NamedBufferedImage] = {

    def isImageFile(p: Path): Boolean =
      Files.isRegularFile(p) &&
        (p.getFileName.toString.toLowerCase().endsWith("jpg") ||
          p.getFileName.toString.toLowerCase().endsWith("jpeg") ||
          p.getFileName.toString.toLowerCase().endsWith("png")) &&
        !p.getFileName.toString.toLowerCase().startsWith("tiles")

    def asBuffered(p: Path): BufferedImage = {
      ImageIO.read(p.toFile)
    }

    Files.list(indir)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toList
      .map(p => NamedBufferedImage(p.getFileName.toString, asBuffered(p)))
      .sortBy(a => a.name)
  }


  private def cropToSquare(borderWidth: Int = 0)(bi: BufferedImage): BufferedImage = {
    val w = bi.getWidth
    val h = bi.getHeight

    def portrait: BufferedImage = {
      val off = ((h - w).toDouble / 2).toInt
      bi.getSubimage(0, off, w, w)
    }

    def landscape: BufferedImage = {
      val off = math.ceil((w - h).toDouble / 2).toInt
      bi.getSubimage(off, 0, h, h)
    }

    if (w == h) bi
    else if (w < h) portrait
    else landscape
  }

  private def scaleSquare(sideLen: Size)(ni: BufferedImage): BufferedImage = {
    Thumbnails.of(ni)
      .forceSize(sideLen.width, sideLen.height)
      .asBufferedImage()
  }

  private def mapNamed(f: BufferedImage => BufferedImage)(ni: NamedBufferedImage): NamedBufferedImage = {
    NamedBufferedImage(ni.name, f(ni.image))
  }


}
