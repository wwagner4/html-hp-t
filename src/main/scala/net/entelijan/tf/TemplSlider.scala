package net.entelijan.tf

import java.io.File
import java.lang.IllegalStateException

class TemplSlider extends Templ {

  def id = "slider"

  override def pages: Iterable[HtmlPage] =
    List(new HtmlPageSlider)

  private class HtmlPageSlider extends HtmlPage {
    def css: String = {
      s"""
         |@font-face {
         | font-family: isonormd;
         | src: url("font/isonormd.ttf") /* TTF file for CSS3 browsers */
         |}
         |body {
         |	margin-top: 0;
         |	font-family: 'isonormd', sans-serif;
         |	background-color: white;
         |	font-size: normal;
         |	font-weight: normal;
         |}
         |h1 {
         |	margin: 0 0 5px;
         |	padding: 0;
         |}
         |p .p1 {
         |	font-family: inherit;
         |	margin: 0;
         |	padding: 0;
         |}
         |.p1 {
         |	max-width: 700px;
         |}
         |img {
         |	margin: 0;
         |	padding: 0;
         |}
         |table {
         |	border-collapse: collapse;
         |	font-size: inherit;
         |}
         |tbody {
         |	font-size: inherit;
         |}
         |tr {
         |	font-size: inherit;
         |}
         |td {
         |	text-align: left;
         |	vertical-align: top;
         |	font-size: inherit;
         |	font-weight: normal;
         |}
         |.col-prize {
         |	text-align: left;
         |	vertical-align: top;
         |	font-size: inherit;
         |	font-weight: normal;
         |    min-width: 140px;
         |}
         |a, img {
         |    border:none;
         |}
         |a {
         |	text-decoration: none;
         |	color: #000000;
         |}
         |a:visited {
         |	text-decoration: none;
         |	color: #000000;
         |}
         |a:hover {
         |	text-decoration: underline;
         |	color: #000000;
         |}
         |a:active {
         |	text-decoration: underline;
         |	color: #000000;
         |}
         |#all {
         |	width: 1200px;
         |	margin:0 auto;
         |}
         |
         |#left {
         |	background-color: white;
         |	width: 400px;
         |	float: left;
         |	padding-bottom: 30px;
         |}
         |
         |#right {
         |	background-color: white;
         |	width: 800px;
         |	float: right;
         |}
         |.sepa {
         |	margin: 8px 0 0;
         |	padding: 0;
         |}
         |.sepa1 {
         |	margin: 8px 0 0;
         |	padding: 0;
         |}
         |.sepa2 {
         |	margin: 20px 0 0;
         |	padding: 0;
         |}
         |.sepa3 {
         |	margin: 40px 0 0;
         |	padding: 0;
         |}
         |.sepa4 {
         |	margin: 60px 0 0;
         |	padding: 0;
         |}
         |.sepa-logos {
         |	margin: 150px 0 0;
         |	padding: 0;
         |}
         |.flex-caption {
         |	width: 100%;
         |	text-align: center;
         |	height: 25px;
         |}
         |#models {
         |	height: 300px;
         |    overflow-y: auto;
         |}
         |
         |.imov_tr {
         |}
         |
         |.imov_table {
         |    width: 100%;
         |}
         |.imov_title {
         |    font-size: 100px;
         |}
         |.imov_txt {
         |    font-size: 15px;
         |    margin-bottom: 26px;
         |    margin-top: 2px;
         |
         |}
         |.imov_img {
         |    width: 300px; /* you can use % */
         |    height: auto;
         |}
         |
         |""".stripMargin
    }

    private def htmlImageList(p: Page): String = {
      val l = imagesFileList(p).zipWithIndex.map { case (f, i) =>
        if (i == 0)
          s"""
               |<li>
               |<img alt="taschenfahrrad" src="${imagesDirPath(
              p
            )}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""".stripMargin
        else
          s"""
               |<li>
               |<img alt="taschenfahrrad" class="lazy" src="#" data-src="${imagesDirPath(
              p
            )}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""".stripMargin
      }
      l.mkString("\n")
    }

    private def imagesDirPath(p: Page): String = s"images/${p.id}"

    private def imagesFileList(p: Page): List[File] = {

      case class F(o: Int, f: File)

      val d = new File(s"src/main/web/common/${imagesDirPath(p)}")
      require(d.exists(), s"directory $d must exist")
      d.listFiles()
        .toList
        .sortBy(f => f.getName)
    }

    // noinspection ScalaUnusedSymbol
    private def htmlPageLinks(pages: List[Page]): String = {
      pages.map(htmlPageLink).mkString("\n")
    }

    private def htmlPageLink(p: Page): String = {
      s"""
         |<p><a href="${fileName(p)}">${p.id}...</a></p>
         |""".stripMargin
    }

    private def htmlContent(p: Page): String =
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
        case Layout_Middle =>
          throw new IllegalStateException("Layout middle not yet implemented")
      }

    def html(p: Page): String =
      s"""
         |<!DOCTYPE html>
         |<html class="no-js" lang="de">
         |<head>
         |<title>das taschenfahrrad</title>
         |<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
         |<meta name="viewport" content="width=device-width, initial-scale=1.0">
         |<link rel="stylesheet" href="flexslider.css" type="text/css" media="screen" />
         |<script src="js/modernizr.js"></script>
         |<style type="text/css">
         |$css
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

}
