package net.entelijan.tf
import net.entelijan.tf.Template.htmlImageList

trait Templ {

  def htmlContetntRight(p: Page): String =
    s"""
       |<div id="right">
       |    <div class="flexslider">
       |    <ul class="slides">
       |${htmlImageList(p)}
       |    </ul>
       |    </div>
       |</div>
       |""".stripMargin
}
