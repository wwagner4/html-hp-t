package net.entelijan.tf

import java.io.File

object Template extends Templ {

  val IMAGES_DIR = "images"

  def htmlImageList(p: Page): String = {
    val l = imagesFileList(p).zipWithIndex.map {
      case (f, i) => p.imageText(f.getName) match {
        case None =>
          if (i == 0)
            s"""
               |<li>
               |<img alt="taschenfahrrad" src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""".stripMargin
          else
            s"""
               |<li>
               |<img alt="taschenfahrrad" class="lazy" data-src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""".stripMargin

        case Some(_) =>
          if (i == 0)
            s"""
               |<li>
               |<img alt="taschenfahrrad" src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption">%s</p>
               |</li>
               |""".stripMargin
          else
            s"""
               |<li>
               |<img class="lazy" data-src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption">%s</p>
               |</li>
               |""".stripMargin

      }
    }
    l.mkString("\n")
  }

  def logoName(p: ImageProvider): String = {
    val f = logoFile(p)
    s"${imagesDirPath(p)}/${f.getName}"
  }

  def imagesDirPath(p: ImageProvider): String = s"$IMAGES_DIR/${p.imageFolder}"

  def imagesFileList(p: ImageProvider): List[File] = {

    case class F(o: Int, f: File)

    @scala.annotation.tailrec
    def order(l: List[(String, Int)], f: File): Option[F] = {
      l match {
        case Nil => None
        case (id, idx) :: rest =>
          if (f.getName.contains(id)) Some(F(idx, f)) else order(rest, f)
      }
    }

    def acceptName(nam: String): Boolean = {
      !nam.toUpperCase().contains("LOGO") &&
        !nam.startsWith(".")
    }

    val d = new File(s"src/main/web/${imagesDirPath(p)}")
    require(d.exists(), s"directory $d must exist")
    d.listFiles()
      .filter(f => acceptName(f.getName))
      .toList
      .sortBy(f => f.getName)
  }

  def logoFile(p: ImageProvider): File = {
    val d = new File(s"src/main/web/${imagesDirPath(p)}")
    require(d.exists(), s"directory $d must exist")
    val l = d.listFiles().filter(_.getName.toUpperCase().contains("LOGO")).toList
    require(l.nonEmpty, s"no logo found for page '${p.id}' in $d")
    require(l.size == 1, s"more than one logo found for page '${p.id}' in $d")
    l.head
  }

  def htmlPageLinks(pages: List[Page]): String = {
    pages.map(htmlPageLink).mkString("\n")
  }

  def htmlPageLink(p: Page): String = {
    s"""
       |<p><a href="${Template.fileName(p)}">${p.name}...</a></p>
       |""".stripMargin
  }

  def htmlTemplate(p: Page): String =
    s"""
       |<!DOCTYPE html>
       |<html class="no-js" lang="de">
       |<head>
       |<title>das taschenfahrrad</title>
       |<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
       |<meta name="viewport" content="width=device-width, initial-scale=1.0">
       |<link href='taschenfahrrad.css'	rel='stylesheet' type='text/css'>
       |<link rel="stylesheet" href="flexslider.css" type="text/css" media="screen" />
       |<script src="js/modernizr.js"></script>
       |<style type="text/css">
       |</style>
       |</head>
       |<body class="load">
       |<div id="all" >
       |${p.htmlContent(this)}
       |</div>
       |<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
       |<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\\x3C/script>')</script>
       |<script defer src="jquery.flexslider.js"></script>
       |<script type="text/javascript">
       |$$(window).load(function() {
       |	  $$('.flexslider').flexslider({
       |	    start: function(slider) { // Fires when the slider loads the first slide
       |	      var slide_count = slider.count - 1;
       |	      $$(slider)
       |	        .find('img.lazy:eq(0)')
       |	        .each(function() {
       |	          var src = $$(this).attr('data-src');
       |	          $$(this).attr('src', src).removeAttr('data-src');
       |	        });
       |	    },
       |	    before: function(slider) { // Fires asynchronously with each slider animation
       |	      var slides     = slider.slides,
       |	          index      = slider.animatingTo,
       |	          $$slide     = $$(slides[index]),
       |	          $$img       = $$slide.find('img[data-src]'),
       |	          current    = index,
       |	          nxt_slide  = current + 1,
       |	          prev_slide = current - 1;
       |
       |	      $$slide
       |	        .parent()
       |	        .find('img.lazy:eq(' + current + '), img.lazy:eq(' + prev_slide + '), img.lazy:eq(' + nxt_slide + ')')
       |	        .each(function() {
       |	          var src = $$(this).attr('data-src');
       |	          $$(this).attr('src', src).removeAttr('data-src');
       |	        });
       |	    }
       |	  });
       |	});
       |</script>
       |</body>
       |</html>
       |""".stripMargin

  def fileName(p: Page): String = "%s.html" format p.id

}

