package net.entelijan.tf.tiles1

import java.nio.file.{Files, Path}
import scala.collection.JavaConverters._


object FilesToUrl {

  def urls(baseDir: Path, pageDir: String): List[String] = {

    def relPath(baseDir: Path, file: Path): String = {
      baseDir.relativize(file).toString
    }

    val dir = baseDir.resolve(pageDir)
    Files.list(dir)
      .iterator()
      .asScala
      .map(file => relPath(baseDir, file))
      .toList
  }

}
