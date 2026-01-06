package net.entelijan.tf

import net.entelijan.tf.imgutil.*
import mainargs.{main, arg, Parser, Flag}

// Docu mainargs can be found in https://github.com/com-lihaoyi/mainargs
object Main {

  @main(name = "gen-homepage", doc = "Generates the homepage")
  def generateHomepage() = {
    val templ = Templs.tiles
    Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)
  }

  @main(name = "prep-images", doc = "Prepares new images to be integrated")
  def prepareImages() = {
    ImgPrepare.main()
  }

  def main(args: Array[String]): Unit =
    Parser(this).runOrExit(args.toIndexedSeq)
}
