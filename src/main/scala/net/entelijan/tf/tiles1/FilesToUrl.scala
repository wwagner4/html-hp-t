package net.entelijan.tf.tiles1

import java.nio.file.{Files, Path}
import scala.jdk.CollectionConverters._

object FilesToUrl {

  def urls(baseDir: Path, pageDir: String): List[String] = {

    def relPath(file: Path): String =
      baseDir.relativize(file).toString

    Files
      .list(baseDir.resolve(pageDir))
      .iterator()
      .asScala
      .map(relPath)
      .toList
      .filter(fn => fn.endsWith("jpg") || fn.endsWith("jpeg"))
      .sorted
  }

}
