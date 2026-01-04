package net.entelijan.tf

import org.rogach.scallop._

class Conf(args: Seq[String]) extends ScallopConf(args) {
  val apples = opt[Int](required = true)
  val bananas = opt[Int]()
  val name = trailArg[String]()
  verify()
}


object Main {

  def main(args: Array[String]): Unit = {
    val conf = new Conf(args.toIndexedSeq)
    println(s"apples are: ${conf.name} ${conf.bananas} ${conf.apples}")
    val x = conf.name;
    println(x.getOrElse("--"))
  }

  //val templ = Templs.tiles
  //Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)

}
