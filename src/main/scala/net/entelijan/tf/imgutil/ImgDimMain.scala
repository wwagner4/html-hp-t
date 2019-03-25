package net.entelijan.tf.imgutil

import java.nio.file.Paths

object ImgDimMain extends App {

  val dir = Paths.get("proto/WebContent/proto04/images/index")
  val dims = ImgDim.fromDir(dir)
  println(dims.mkString("\n"))
}
