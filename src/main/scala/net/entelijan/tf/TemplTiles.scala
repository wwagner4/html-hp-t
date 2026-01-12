package net.entelijan.tf

import java.nio.file.Paths
import java.util.Locale

import net.entelijan.tf.tiles1.TableUtil

class TemplTiles extends Templ {

  override def id = "tiles"

  override def pages: Iterable[HtmlPage] =
    List(new HtmlPageMain, new HtmlPageGlide)

  class HtmlPageGlide extends HtmlPage {

    private def glideTable(p: Page): String = {
      val baseDir = Paths.get("src/main/web/common")
      TableUtil.glideTable(baseDir, s"images/${p.id}")
    }

    override def html(p: Page): String =
      s"""
         |<!DOCTYPE html>
         |<html class="no-js" lang="de">
         |<head>
         |    <title>das taschenfahrrad</title>
         |    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
         |    <meta name="viewport" content="width=device-width, initial-scale=1.0">
         |    <link rel="stylesheet" href="glide/css/glide.core.min.css">
         |    <link rel="stylesheet" href="glide/css/glide.theme.min.css">
         |    <style>
         |        html {
         |            -webkit-tap-highlight-color:transparent;
         |        }
         |        body {
         |            margin: 0;
         |            background-color: white;
         |            overflow: hidden;
         |        }
         |        .fill {
         |            width: 100vw;
         |            height: 100vh;
         |            background-position: center;
         |            background-size: contain;
         |            background-repeat: no-repeat;
         |            background-color: white;
         |        }
         |        .glide {
         |            z-index: 10;
         |        }
         |        .glide__slides {
         |            margin: 0;
         |        }
         |        #closeb {
         |            background-image: url(css/cross.png);
         |            background-color: transparent;
         |            background-repeat: no-repeat;
         |            background-size: 15% 15%;
         |            right: 10px;
         |            position: absolute;
         |            top: 10px;
         |            left: 10px;
         |            width: 150px;
         |            height: 150px;
         |            z-index: 30;
         |            cursor: pointer;
         |        }
         |        .butto-left {
         |            background-image: url(css/arrow-left.png);
         |            background-size: 50% 50%;
         |            background-repeat: no-repeat;
         |            left: 10px;
         |            cursor: pointer;
         |            user-select: none;
         |        }
         |        .butto-right {
         |            background-image: url(css/arrow-right.png);
         |            background-size: 50% 50%;
         |            background-repeat: no-repeat;
         |            right: -10px;
         |            cursor: pointer;
         |            user-select: none;
         |        }
         |        .button-back {
         |            position: absolute;
         |            display: block;
         |            top: 50%;
         |            z-index: 2;
         |            width: 50px;
         |            height: 50px;
         |            cursor: pointer;
         |            background-color: rgba(255, 255, 255, 0);
         |            border-width: 0;
         |            outline: none;
         |            user-select: none;
         |        }
         |    </style>
         |</head>
         |<body class="load">
         |<div class="glide">
         |    <div id="closeb" onclick="window.history.back();"></div>
         |${glideTable(p)}
         |    <div class="glide__arrows" data-glide-el="controls">
         |        <button class="button-back butto-left" data-glide-dir="<"></button>
         |        <button class="button-back butto-right" data-glide-dir=">"></button>
         |    </div>
         |</div>
         |<script src="glide/glide.min.js"></script>
         |<script>
         |    let urlParams = new URLSearchParams(window.location.search);
         |    //console.log("urlParams type:'" + typeof urlParams + "'")
         |    var index = 0;
         |    if (urlParams.has("index")) {
         |        // console.log("urlParams:'" + urlParams + "'")
         |        index = parseInt(urlParams.get('index'));
         |    } 
         |    // console.log("index:'" + index + "'")
         |    let glide = new Glide('.glide', {
         |        type: 'carousel',
         |        startAt: index,
         |        perView: 1,
         |        gap: 0
         |    }).mount();
         |    let tiles_length = 8;
         |    let tiles_cols = 3;
         |    let tiles_tileSize = 295;
         |</script>
         |</body>
         |</html>
         |
         |""".stripMargin

    def fileName(p: Page): String = "%sGlide.html" format p.id

  }

  class HtmlPageMain extends HtmlPage {

    case class CssParameters(
        contentWidth: Double,
        leftPercentage: Double,
        tilesPadding: Double,
        rows: Int,
        columns: Int
    ) {

      def tilesSize: Double = {
        val rightWidth = contentWidth * (rightPercentage / 100.0)
        val paddings = 2 * tilesPadding
        (rightWidth / columns) - paddings
      }

      def rightPercentage: Double = {
        100.0 - leftPercentage
      }
    }

    def fmt(value: Double, unit: String): String =
      "%.3f%s".formatLocal(Locale.ENGLISH, value, unit)

    def params(p: Page): CssParameters = {
      val contentWidth = 75 // em
      val tilesPadding = 0.3 // em
      p.layout match {
        case Layout_Default =>
          CssParameters(contentWidth, 30, tilesPadding, 4, 4)
        case Layout_Middle =>
          CssParameters(contentWidth, 37, tilesPadding, 4, 3)
        case Layout_Wide => CssParameters(contentWidth, 45, tilesPadding, 3, 2)
      }
    }

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
         |    font-size: 15px;
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
         |}
         |
         |#right {
         |    float: left;
         |    width: ${fmt(par.rightPercentage, "%")};
         |    padding: 0 0.3em 0.3em 0;
         |    height: inherit;
         |}
         |
         |.thumb {
         |    object-fit: cover;
         |    width: ${fmt(par.tilesSize, "em")};
         |    height: ${fmt(par.tilesSize, "em")};
         |    vertical-align: top;
         |    cursor: pointer;
         |}
         |
         |.col-prize {
         |	 text-align: left;
         |	 vertical-align: top;
         |	 font-size: inherit;
         |	 font-weight: normal;
         |   min-width: 4.6em;
         |}
         |.col-prize1 {
         |	 text-align: left;
         |	 vertical-align: top;
         |	 font-size: inherit;
         |	 font-weight: normal;
         |   min-width: 5em;
         |}
         |.rTable {
         |    display: table;
         |}
         |
         |.rTableRow {
         |    display: table-row;
         |}
         |
         |.rTableCell {
         |    display: table-cell;
         |    padding: 0 ${fmt(par.tilesPadding, "em")} ${fmt(
          par.tilesPadding,
          "em"
        )} 0;
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
         |.p2 {
         |	font-family: inherit;
         |}
         |img {
         |	margin: 0;
         |	padding: 0;
         |}
         |
         |td {
         |  vertical-align: top;
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
         |	margin: 50px 0 0;
         |	padding: 0;
         |}
         |.sepa4 {
         |	margin: 80px 0 0;
         |	padding: 0;
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
      TableUtil.htmlTable(
        p.id,
        baseDir,
        s"images/${p.id}",
        par.rows,
        par.columns
      )
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
         |<style type="text/css">
         |${css(par)}
         |</style>
         |</head>
         |<body class="load">
         |${htmlContent(p, par)}
         |</body>
         |</html>
         |""".stripMargin
    }

    def fileName(p: Page): String = "%s.html" format p.id

  }

}
