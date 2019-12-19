package net.entelijan.tf

trait HtmlPage {

  def html(p: Page): String

  def fileName(p: Page): String

}

trait Templ {

  def id: String

  def pages: Iterable[HtmlPage]
}
