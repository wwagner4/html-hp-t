package net.entelijan.tf

trait Templ {
  def html(p: Page): String

  def fileName(p: Page): String

}
