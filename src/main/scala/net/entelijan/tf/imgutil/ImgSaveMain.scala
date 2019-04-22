package net.entelijan.tf.imgutil

import java.awt.image.BufferedImage
import java.nio.file.{Files, Path, Paths}

import javax.imageio.ImageIO
import net.entelijan.tf.tiles.TilesFromDirectory


object ImgSaveMain extends App {

  val qualities = Seq(0.2, 0.5, 0.8, 1.0)

  val formats = Seq(ImgFormat_JPG, ImgFormat_PNG)

  for (f <- formats; q <- qualities) {
    saveImage(f, q)
  }

  def createTestImage(outDir: Path): BufferedImage = {
    val inDir = Paths.get("proto/WebContent/proto04/images/index")
    val path = TilesFromDirectory.squaredTiles("orig", 4, 300, 0, ImgFormat_PNG, 1.0, inDir, outDir)
    ImageIO.read(path.toFile)
  }

  def saveImage(imgFormat: ImgFormat, quality: Double): Unit = {
    val qs1 = "%03d".format((quality * 100).toInt)
    val qs2 = "%.2f".format(quality)
    val name = s"qtest$qs1"
    val attr = ImgAttr(
      name, imgFormat, quality
    )
    val outDir = Paths.get("target", "qtest01")
    val testImg = createTestImage(outDir)
    val out = ImgSave.save(testImg, attr, outDir)
    println(s"Saved image with quality $qs2 to $out")
  }

}
