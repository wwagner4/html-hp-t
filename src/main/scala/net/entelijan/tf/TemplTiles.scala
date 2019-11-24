package net.entelijan.tf

import java.io.File
import java.nio.file.Paths
import java.util.Locale

import net.entelijan.tf.tiles1.TableUtil

class TemplTiles extends Templ {

  case class CssParameters(contentWidth: Double, leftPercentage: Double, tilesPadding: Double,  rows: Int, columns: Int) {

    def tilesSize: Double = {
      (contentWidth * (rightPercentage / 100.0)) / columns
    }

    def marginTop: Double ={
      - (2 * tilesPadding)
    }
    
    def rightPercentage: Double = {
      100.0 - leftPercentage
    }
  }

  def fmt(value: Double, unit: String): String = "%.3f%s".formatLocal(Locale.ENGLISH, value, unit)

  def params(p: Page): CssParameters = {
    val contentWidth = 75 // em
    val tilesPadding = 0.3 // em
    p.layout match {
      case Layout_Default => CssParameters(contentWidth, 25, tilesPadding, 4, 4)
      case Layout_Wide => CssParameters(contentWidth, 55, tilesPadding, 6, 3)
    }
  }

  def id = "tiles"

  def css(par: CssParameters): String = {
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
       |    width: ${fmt(par.contentWidth, "em")};
       |    height: 100vh;
       |    margin-top: 0;
       |}
       |
       |#left {
       |    float: left;
       |    width: ${fmt(par.leftPercentage, "%")};
       |    padding: 0.3em 0.3em 0.3em 0.3em;
       |    height: inherit;
       |    overflow: auto;
       |}
       |
       |#right {
       |    float: left;
       |    width: ${fmt(par.rightPercentage, "%")};
       |    padding: 0.3em 0.3em 0.3em 0;
       |    height: inherit;
       |    overflow: auto;
       |}
       |
       |.thumb {
       |    object-fit: contain;
       |    width: ${fmt(par.tilesSize, "em")};
       |    height: ${fmt(par.tilesSize, "em")};
       |    background-position-x: ${fmt(par.tilesPadding, "em")};
       |    background-position-y: ${fmt(par.tilesPadding, "em")};
       |    background-size: ${fmt(par.tilesSize, "em")} ${fmt(par.tilesSize, "em")};
       |    background-repeat: no-repeat;
       |}
       |
       |.col-prize {
       |	 text-align: left;
       |	 vertical-align: top;
       |	 font-size: inherit;
       |	 font-weight: normal;
       |   min-width: 16em;
       |}
       |.col-prize1 {
       |	 text-align: left;
       |	 vertical-align: top;
       |	 font-size: inherit;
       |	 font-weight: normal;
       |   min-width: 10em;
       |}
       |.rTable {
       |    display: table;
       |    margin-top: ${fmt(par.marginTop, "em")};
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

  private def htmlTable(p: Page, par: CssParameters): String = {
    val baseDir = Paths.get("src/main/web/common")
    TableUtil.htmlTable(baseDir, s"images/${p.id}", par.rows, par.columns)
  }

  private def htmlContent(p: Page, par: CssParameters): String =
    s"""
       |<div id="cont">
       |<div id="left">
       |${p.htmlContentLeftPage}
       |</div>
       |<div id="right">
       |${htmlTable(p, par)}
       |</div>
       |</div>
       |""".stripMargin


  def html(p: Page): String = {

    val par = params(p)

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
       |${
      css(par)
    }
       |</style>
       |</head>
       |<body class="load">
       |${
      htmlContent(p, par)
    }
       |</body>
       |</html>
       |""".stripMargin
  }

  def fileName(p: Page): String = "%s.html" format p.id

}

