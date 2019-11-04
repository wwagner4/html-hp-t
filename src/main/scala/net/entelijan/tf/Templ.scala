package net.entelijan.tf

trait Templ {

  def htmlImageList(p: Page): String


  def htmlContent(p: Page): String =
    s"""
       |${htmlContetntLeft(p)}
       |${htmlContetntRight(p)}
       |""".stripMargin


  def htmlContetntLeft(p: Page): String =
    s"""
       |<div id="left">
       |${MyMarkdown.md(p.markdownLeft)}
       |</div>
       | """.stripMargin

  def htmlContetntRight(p: Page): String =
    s"""
       |<div id="right">
       |    <div class="flexslider">
       |    <ul class="slides">
       |${htmlImageList(p)}
       |    </ul>
       |    </div>
       |</div>
       | """.stripMargin
}
