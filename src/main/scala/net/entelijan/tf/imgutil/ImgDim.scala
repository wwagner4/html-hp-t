package net.entelijan.tf.imgutil

import java.io.File
import java.nio.file.{Files, Path}

import javax.imageio.ImageIO
import javax.imageio.stream.FileImageInputStream

import scala.collection.JavaConverters._

object ImgDim {

  def fromDir(dir: Path): Seq[Dim] = {
    Files.list(dir)
      .iterator()
      .asScala
      .toStream
      .flatMap(p => dim(p))
  }

  private def dim(file: Path): Option[Dim] =
    Some(file)
      .flatMap(toRegularFile)
      .flatMap(file => toImageDimension(file))

  private def toRegularFile(file: Path): Option[File] =
    if (Files.isRegularFile(file)) Some(file.toFile)
    else None

  private def toImageDimension(imgFile: File): Option[Dim] = {
    val name = imgFile.getName
    val pos = name.lastIndexOf(".")
    if (pos == -1) None
    else {
      val suffix = name.substring(pos + 1).toLowerCase
      ImageIO.getImageReadersBySuffix(suffix)
        .asScala
        .toStream
        .map { reader =>
          try {
            val stream = new FileImageInputStream(imgFile)
            reader.setInput(stream)
            val width = reader.getWidth(reader.getMinIndex)
            val height = reader.getHeight(reader.getMinIndex)
            Dim(name, suffix, width, height)
          } finally reader.dispose()
        }.headOption
    }
  }

}
