package net.entelijan.tf

import laika.api.{Transformer}
import laika.format._
import laika.parse.markup.DocumentParser

import scala.io.Codec

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

    Transformer.from(Markdown).to(HTML).build.transform(in) match {
      case Left(e: DocumentParser.ParserError) => throw new IllegalArgumentException(e)
      case Right(str: String) => sth(str)
    }
  }
}

