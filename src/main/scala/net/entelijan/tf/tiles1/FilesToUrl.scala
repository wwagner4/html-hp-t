package net.entelijan.tf.tiles1

import java.nio.file.{Files, Path}
import scala.collection.JavaConverters._


object FilesToUrl {

  def urls(baseDir: Path, pageDir: String): List[String] = {

    def relPath(file: Path): String =
      baseDir.relativize(file).toString
    
    Files.list(baseDir.resolve(pageDir))
      .iterator()
      .asScala
      .map(relPath)
      .toList
  }

}
