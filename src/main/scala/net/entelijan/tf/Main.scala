package net.entelijan.tf

import net.entelijan.tf.imgutil.*
import net.entelijan.tf.util.*
import mainargs.{main, arg, Parser, Flag}

// Docu mainargs can be found in https://github.com/com-lihaoyi/mainargs
object Main {

  @main(name = "generate", doc = "Generates the homepage")
  def generateHomepage() = {
    val templ = Templs.tiles
    Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)
  }

  @main(name = "prepare", doc = "Prepares new images to be integrated")
  def prepareImages() = {
    ImgPrepare.main()
  }

  @main(name = "overview", doc = "Creates an overview of the current images")
  def overviewImages() = {
    ImgOverview.main()
  }

  @main(
    name = "finalize",
    doc =
      "Create the final image directories based on the results from overview"
  )
  def _finalize() = {
    finalizeMain()
  }

  @main(
    name = "tryout",
    doc = "Tryout something. To be used during development"
  )
  def tryout() = {
    tryoutMain()
  }

  def main(args: Array[String]): Unit =
    Parser(this).runOrExit(args.toIndexedSeq)
}
