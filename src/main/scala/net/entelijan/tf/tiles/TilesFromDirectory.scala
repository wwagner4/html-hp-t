package net.entelijan.tf.tiles

import java.awt.image.BufferedImage
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO

import scala.collection.JavaConverters._

object TilesFromDirectory {

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
    val bimages = Files.list(indir)
      .filter(p => isImageFile(p))
      .iterator()
      .asScala
      .toList
      .map(p => (p.getFileName.toString, asBuffered(p)))
    val images = for ((id, bi) <- bimages) yield {
      Image(id = id, size = Size(bi.getWidth, bi.getHeight))
    }
    val gr = Geometry.tiles(300, 4)(images)
    println(gr.mkString("\n"))


  }

}
