package net.entelijan.tf.imgutil

import java.awt.Image
import java.awt.image.RenderedImage
import java.nio.file.{Files, Path}

import javax.imageio.stream.FileImageOutputStream
import javax.imageio.{IIOImage, ImageIO, ImageWriteParam, ImageWriter}

sealed trait ImgFormat {

  def ext: String

}

case object ImgFormat_JPG extends ImgFormat {
  override def ext: String = "jpg"
}

case object ImgFormat_PNG extends ImgFormat {
  override def ext: String = "png"
}

case class ImgAttr(
    name: String, // Name without extention
    format: ImgFormat,
    compressionQuality: Double // 0 - lowest quality, 1 - highest quality
)

object ImgSave {

  /** Saves an image according to the attributes
    *
    * @param image
    *   Java representation of the image
    * @param imgAttr
    *   Attributes of how to save the image
    * @param outDir
    *   Output directory. Gets created if not exist.
    * @return
    *   Absolute path to the saved file
    */
  def save(image: RenderedImage, imgAttr: ImgAttr, outDir: Path): Path = {
    if (!Files.exists(outDir)) Files.createDirectories(outDir)
    val iter = ImageIO.getImageWritersByFormatName(imgAttr.format.ext)
    val writer = iter.next()
    try {
      val iwp = writer.getDefaultWriteParam
      iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT)
      iwp.setCompressionQuality(imgAttr.compressionQuality.toFloat)
      val fname = s"${imgAttr.name}.${imgAttr.format.ext}"
      val outPath = outDir.resolve(fname)
      val os = new FileImageOutputStream(outPath.toFile)
      writer.setOutput(os)
      val iio = new IIOImage(image, null, null)
      writer.write(null, iio, iwp)
      outPath
    } finally {
      writer.dispose()
    }
  }

}
