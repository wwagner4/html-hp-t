package net.entelijan.tf

import scala.language.postfixOps

object MarkdownTryout extends App {

  println(MyMarkdown.md("""
##HALLO
--12€-- 13€
"""))

}