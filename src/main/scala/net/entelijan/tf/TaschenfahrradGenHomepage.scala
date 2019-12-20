package net.entelijan.tf

object TaschenfahrradGenHomepage extends App {

  val templ = Templs.tiles
  Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)

}
