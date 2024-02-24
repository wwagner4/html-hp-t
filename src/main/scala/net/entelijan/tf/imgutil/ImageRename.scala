package net.entelijan.tf.imgutil

import java.nio.file.{Files, Path, Paths}

import scala.jdk.CollectionConverters._


object ImageRename {

  def main(): Unit = {
    // TODO add to commandline interface
    val dir = Paths.get("/home/wwagner4/tmp")

    Files.list(dir)
      .iterator()
      .asScala.toList
      .filter(p => !p.getFileName.toString.startsWith("."))
      .sortBy(p => p.getFileName.toString)
      .zipWithIndex
      .map { case (p, i) => Files.copy(p, newPath(p, i)) }
  }

  def newPath(oldPath: Path, index: Int): Path = {
    val p = oldPath.getParent
    val out = p.resolve("out")
    Files.createDirectories(out)
    out.resolve(s"tf_0223_$index.jpg")
  }

}
