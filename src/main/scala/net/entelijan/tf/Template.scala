package net.entelijan.tf

import java.io.File

class Template extends Templ {

  def htmlImageList(p: Page): String = {
    val l = imagesFileList(p).zipWithIndex.map {
      case (f, i) => {
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
             |<img alt="taschenfahrrad" class="lazy" src="#" data-src="${imagesDirPath(p)}/${f.getName}" />
             |<p class="flex-caption"></p>
             |</li>
             |""".stripMargin

      }
    }
    l.mkString("\n")
  }

  def imagesDirPath(p: Page): String = s"images/${p.id}"

  def imagesFileList(p: Page): List[File] = {

    case class F(o: Int, f: File)

    val d = new File(s"src/main/web/${imagesDirPath(p)}")
    require(d.exists(), s"directory $d must exist")
    d.listFiles()
      .toList
      .sortBy(f => f.getName)
  }

  def htmlPageLinks(pages: List[Page]): String = {
    pages.map(htmlPageLink).mkString("\n")
  }

  def htmlPageLink(p: Page): String = {
    s"""
       |<p><a href="${fileName(p)}">${p.id}...</a></p>
       |""".stripMargin
  }

  def htmlContent(p: Page): String =
    p.layout match {
      case Layout_Default =>
        s"""
           |<div id="all" >
           |<div id="left">
           |${p.htmlContentLeftPage}
           |</div>
           |<div id="right">
           |    <div class="flexslider">
           |    <ul class="slides">
           |${htmlImageList(p)}
           |    </ul>
           |    </div>
           |</div>
           |</div>
           |""".stripMargin
      case Layout_Wide =>
        s"""
           |<div id="all" >
           |${p.htmlContentLeftPage}
           |</div>
           |""".stripMargin
    }


  def html(p: Page): String =
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
       |${htmlContent(p)}
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

