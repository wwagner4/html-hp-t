package net.entelijan.tf

import java.io.File
import java.nio.file.Paths

import net.entelijan.tf.tiles1.TableUtil
import net.entelijan.tf.tiles1.TableUtilTryout.baseDir

class TemplTiles extends Templ {

  def id = "tiles"

  def css: String = {
    s"""
      |
      |@font-face {
      |    font-family: isonormd;
      |    src: url("font/isonormd.ttf")
      |}
      |
      |body {
      |    font-family: 'isonormd', sans-serif;
      |    background-color: white;
      |    font-size: medium;
      |    font-weight: normal;
      |    margin: 0;
      |}
      |
      |h1 {
      |    margin: 0 0 1em 0;
      |    padding: 0;
      |}
      |
      |p {
      |    margin: 0 0 0.5em 0 ;
      |    padding: 0;
      |}
      |
      |* {
      |    box-sizing: border-box;
      |}
      |
      |#cont {
      |    margin-left: auto;
      |    margin-right: auto;
      |    width: 47em;
      |    height: 100vh;
      |    margin-top: 0;
      |}
      |
      |#left {
      |    float: left;
      |    width: 35%;
      |    padding: 0.3em 0.3em 0.3em 0.3em;
      |    height: inherit;
      |    overflow: auto;
      |}
      |
      |#right {
      |    float: left;
      |    width: 65%;
      |    padding: 0.3em 0.3em 0.3em 0;
      |    height: inherit;
      |    overflow: auto;
      |}
      |
      |.thumb {
      |    object-fit: contain;
      |    width: 15em;
      |    height: 15em;
      |    background-position-x: 3px;
      |    background-position-y: 3px;
      |    background-size: 15em 15em;
      |    background-repeat: no-repeat;
      |}
      |
      |.rTable {
      |    display: table;
      |    margin-top: -5px;
      |}
      |
      |.rTableRow {
      |    display: table-row;
      |}
      |
      |.rTableCell {
      |    display: table-cell;
      |}
      |
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
      |
      |""".stripMargin
  }

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

    val d = new File(s"src/main/web/common/${imagesDirPath(p)}")
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

  def htmlTable(p: Page): String = {
    val baseDir = Paths.get("src/main/web/common")
    TableUtil.htmlTable(baseDir, s"images/${p.id}", 3, 4)
  }

  def htmlContent(p: Page): String =
    p.layout match {
      case Layout_Default =>
        s"""
           |<div id="cont">
           |<div id="left">
           |${p.htmlContentLeftPage}
           |</div>
           |<div id="right">
           |${htmlTable(p)}
           |</div>
           |</div>
           |""".stripMargin
      case Layout_Wide =>
        s"""
           |<div id="cont">
           |<div id="left">
           |${p.htmlContentLeftPage}
           |</div>
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
       |<link rel="stylesheet" href="flexslider.css" type="text/css" media="screen" />
       |<script src="js/modernizr.js"></script>
       |<style type="text/css">
       |${css}
       |</style>
       |</head>
       |<body class="load">
       |${htmlContent(p)}
       |</body>
       |</html>
       |""".stripMargin

  def fileName(p: Page): String = "%s.html" format p.id

}

