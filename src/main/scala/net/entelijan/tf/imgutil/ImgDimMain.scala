package net.entelijan.tf.imgutil

import java.nio.file.Paths

object ImgDimMain {

  def main(): Unit = {
    // TODO add to commandline
    val dir = Paths.get("proto/WebContent/proto04/images/index")
    val dims = ImgDim.fromDir(dir)
    println(dims.mkString("\n"))
  }
}
