package net.entelijan.tf

trait Templ {

  def id: String

  def html(p: Page): String

  def fileName(p: Page): String

}
