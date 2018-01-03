package net.entelijan.tf

import laika.parse.markdown.Markdown
import laika.api.Render
import laika.api.Parse
import scala.io.Codec
import laika.render.HTML

object MyMarkdown {

  def md(in: String): String = {
    def sth(text: String): String = {
      val st = """(?s)(.*)--(.*)--(.*)""".r
      text match {
        case st(a, b, c) => 
          val a1 = sth(a)
          s"$a1<s>$b</s>$c"
        case any => any
      }
    }
    implicit val codec: Codec = Codec.UTF8
    val internal = Parse as Markdown fromString in
    val x = (Render as HTML from internal).toString
    sth(x)
  }
}

