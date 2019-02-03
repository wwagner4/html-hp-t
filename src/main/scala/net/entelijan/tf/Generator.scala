package net.entelijan.tf

import java.io.{File, PrintWriter}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileSystems, Files, Path}

trait ImageProvider {
  def id: String

  def imageFolder: String = id
}

trait Page extends ImageProvider {
  def name: String

  def htmlContent: String

  /**
    * Defines a text to an image.
    * Filename of the image can be used to identify the image
    */
  def imageText(fnam: String): Option[String] = None
}

trait OverviewPage extends Page {
  def pages: List[Page]
}

object T {

  val IMAGES_DIR = "images"

  def htmlImageList(p: Page): String = {
    val l = imagesFileList(p).zipWithIndex.map { case (f, i) => p.imageText(f.getName) match {
      case None =>
        if (i == 0)
          """
      <li>
          <img src="%s/%s" />
          <p class="flex-caption"></p>
      </li>
      """ format(imagesDirPath(p), f.getName)
        else
          """
      <li>
          <img class="lazy" data-src="%s/%s" />
          <p class="flex-caption"></p>
      </li>
      """ format(imagesDirPath(p), f.getName)

      case Some(txt) =>
        if (i == 0)
          """
      <li>
          <img src="%s/%s" />
          <p class="flex-caption">%s</p>
      </li>
      """ format(imagesDirPath(p), f.getName, txt)
        else
          """
      <li>
          <img class="lazy" data-src="%s/%s" />
          <p class="flex-caption">%s</p>
      </li>
      """ format(imagesDirPath(p), f.getName, txt)

    }
    }
    l.mkString("\n")
  }

  def logoName(p: ImageProvider): String = {
    val f = logoFile(p)
    """%s/%s""" format(imagesDirPath(p), f.getName)
  }

  def imagesDirPath(p: ImageProvider): String = "%s/%s" format(IMAGES_DIR, p.imageFolder)

  def imagesFileList(p: ImageProvider): List[File] = {

    case class F(o: Int, f: File)

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

    val d = new File("src/main/web/%s" format imagesDirPath(p))
    require(d.exists(), "directory %s must exist" format d)
    d.listFiles()
      .filter(f => acceptName(f.getName))
      .toList
      .sortBy(f => f.getName)
  }

  def logoFile(p: ImageProvider): File = {
    val d = new File("src/main/web/%s" format imagesDirPath(p))
    require(d.exists(), "directory %s must exist" format d)
    val l = d.listFiles().filter(_.getName.toUpperCase().contains("LOGO")).toList
    require(l.nonEmpty, "no logo found for page '%s' in %s" format(p.id, d))
    require(l.size == 1, "more than one logo found for page '%s' in %s" format(p.id, d))
    l(0)
  }

  def htmlPageLinks(pages: List[Page]): String = {
    pages.map(htmlPageLink).mkString("\n")
  }

  def htmlPageLink(p: Page): String = {
    s"""
    <p><a href="%s">%s...</a></p>
    """ format(T.fileName(p), p.name)
  }

  def htmlContetntRight(p: Page): String =
    s"""
<div id="right">
    <div class="flexslider">
    <ul class="slides">
${htmlImageList(p)}
    </ul>
    </div>
</div>
"""

  def htmlTemplate(p: Page): String =
    s"""
<!DOCTYPE html>
<html class="no-js">
<head>
<title>das taschenfahrrad</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href='taschenfahrrad.css'	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="flexslider.css" type="text/css" media="screen" />
<script src="js/modernizr.js"></script>
<style type="text/css">
</style>
</head>
<body class="load">
<div id="all">
${p.htmlContent}
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.min.js">\\x3C/script>')</script>
<script defer src="jquery.flexslider.js"></script>
<script type="text/javascript">
$$(window).load(function() {
	  $$('.flexslider').flexslider({
	    start: function(slider) { // Fires when the slider loads the first slide
	      var slide_count = slider.count - 1;
	      $$(slider)
	        .find('img.lazy:eq(0)')
	        .each(function() {
	          var src = $$(this).attr('data-src');
	          $$(this).attr('src', src).removeAttr('data-src');
	        });
	    },
	    before: function(slider) { // Fires asynchronously with each slider animation
	      var slides     = slider.slides,
	          index      = slider.animatingTo,
	          $$slide     = $$(slides[index]),
	          $$img       = $$slide.find('img[data-src]'),
	          current    = index,
	          nxt_slide  = current + 1,
	          prev_slide = current - 1;

	      $$slide
	        .parent()
	        .find('img.lazy:eq(' + current + '), img.lazy:eq(' + prev_slide + '), img.lazy:eq(' + nxt_slide + ')')
	        .each(function() {
	          var src = $$(this).attr('data-src');
	          $$(this).attr('src', src).removeAttr('data-src');
	        });
	    }
	  });
	});
</script>
</body>
</html>
    """

  def fileName(p: Page): String = "%s.html" format p.id

}

object D {

  val startPage: Page = new Page {
    def id = "index"

    def name = "start"

    def htmlContent: String =
      s"""
         |<div id="left">
         |<h1>das taschenfahrrad</h1>
         |<p id="sepa2" />
         |<p>stadt-, tourenräder und fahrradtaschen<p>
         |<p id="sepa2" />
         |<p>Verkauf / Werkstatt<p>
         |<p>di-fr: 13-18:00 sa 9-15</p>
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">instagram...</a></p>
         |<p id="sepa"/>
         |<p>kontakt <a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p id="sepa2"/>
         |
         |<p>Wer radlos, ist kann im taschenfahrrad eine treffende Beratung
         |und mit etwas Glück gleich das passende Rad finden.
         |Wir haben uns das Knowhow in vielen Jahren als Rennradler,
         |Reiseradler, Fahrradkurier er-fahren, wissen, wo es zwicken
         |kann und wie es sich anfühlt, wenn alles passt. Unsere visits
         |sind die vielen großartigen Räder, die wir Dank unserer Kunden
         |bauen konnten.<br>
         |Das 2010 gegründete 'taschenfahrrad' bietet Räder für alltägliche
         |Stadtfahrten und Überlandfahrten bis Weltreisen.
         |Wir bevorzugen Räder mit schlankem Stahlrahmen,
         |die sowohl komfortabel als auch robust sind, mit möglichst einfacher lang-
         |lebiger Technik. Räder, die sich schnell, unkompliziert und
         |günstig servicieren lassen. Wenn der Markt uns das nicht bieten
         |kann, bauen wir die Räder selbst.<br>
         |Wir lieben schnelle Räder, die auch praktisch sind und wir wollen
         |schöne Räder, weil wir gerne und mit Stolz radfahren.
         |</p>
         |
         |<p id="sepa2"/>
         |<p><a href="producer.html">unsere Markenräder...</a></p>
         |
         |<p id="sepa"/>
         |<p><a href="selfmade.html">unsere Eigenbauräder...</a></p>
         |
         |<p id="sepa"/>
         |<p><a href="service.html">unser Service...</a></p>
         |
         |<p id="sepa"/>
         |<p><a href="jobs.html">unser Jobangebot...</a></p>
         |
         |</div>
         |${T.htmlContetntRight(this)}
         |""".stripMargin
  }


  val producerPage: Page = new Page {
    def id = "producer"

    def name = "fahrräder"

    def htmlContent: String =
      s"""|<div id="left">
          |   <h1><a href="index.html">das taschenfahrrad</a></h1>
          |   <p><a href="index.html">start</a> &#62; $name</p>
          |   <p id="sepa3"/>
          |
          |   <p>TODO</p>
          |
          |</div>
          |${T.htmlContetntRight(this)}
          """.stripMargin
  }

  val selfmadePage: Page = new Page {
    def id = "selfmade"

    def name = "eigenbau"

    def htmlContent: String =
      s"""|<div id="left">
          |   <h1><a href="index.html">das taschenfahrrad</a></h1>
          |   <p><a href="index.html">start</a> &#62; $name</p>
          |   <p id="sepa3"/>
          |
          |   <p>TODO</p>
          |
          |</div>
          |${T.htmlContetntRight(this)}
          """.stripMargin
  }

  val jobsPage: Page = new Page {
    def id = "jobs"

    def name = "jobs"

    def htmlContent: String =
      s"""|<div id="left">
          |   <h1><a href="index.html">das taschenfahrrad</a></h1>
          |   <p><a href="index.html">start</a> &#62; $name</p>
          |   <p id="sepa3"/>
          |
          |   <p>TODO</p>
          |
          |</div>
          |${T.htmlContetntRight(this)}
          """.stripMargin
  }

  val servicePage: Page = new Page {
    def id = "service"

    def name = "service"

    def htmlContent: String =
      s"""
         |<div id="left">
         |<h1><a href="index.html">das taschenfahrrad</a></h1>
         |<p><a href="index.html">start</a> &#62; $name</p>
         |<p id="sepa3"/>
         |
         |<p>TODO</p>
         |
         |</div>
         |${T.htmlContetntRight(this)}
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

object G {

  def gen(outDir: File): Unit = {
    ResCopy.copy(new File("src/main/web"), outDir)
    D.pages.foreach(genPage(_, outDir))
    println("finished generation of taschenfahrrad in %s" format outDir.getCanonicalPath)
  }

  private def genPage(p: Page, outDir: File): Unit = {
    p match {
      case op: OverviewPage =>
        genSinglePage(op, outDir)
        op.pages.foreach(genPage(_, outDir))
      case p1: Page => genSinglePage(p1, outDir)
    }
  }

  private def genSinglePage(p: Page, outDir: File): Unit = {
    val fnam = T.fileName(p)
    val f = new File(outDir, fnam)
    val pw = new PrintWriter(f, "UTF-8")
    pw.print(T.htmlTemplate(p).trim())
    pw.close()
    println("wrote to %s" format f)
  }

}

object ResCopy {

  def copy(from: File, to: File) {
    require(from.isDirectory, "%s is not a directory" format from)
    require(to.isDirectory, "%s is not a directory" format to)
    val toFiles = to.listFiles().toList
    for (fromFile <- from.listFiles()) {
      if (fromFile.isDirectory) {
        findFile(fromFile.getName, toFiles) match {
          case None =>
            val newDir = new File(to, fromFile.getName)
            newDir.mkdirs()
            copy(fromFile, newDir)
          case Some(toFile) => copy(fromFile, toFile)
        }
      } else {
        findFile(fromFile.getName, toFiles) match {
          case None => copyFile(fromFile, to)
          case Some(toFile) => if (leftIsYounger(fromFile, toFile)) copyFile(fromFile, to)
          //else println("no copy of %s to %s because younger exists" format (fromFile, to))
        }
      }
    }

  }

  def copyFile(f: File, dir: File): Unit = {
    import java.io.{File, FileInputStream, FileOutputStream}
    require(dir.isDirectory, "%s is not a directory" format dir)
    val newFile = new File(dir, f.getName)
    new FileOutputStream(newFile) getChannel() transferFrom(
      new FileInputStream(f).getChannel, 0, Long.MaxValue)
    println("copied %s to %s" format(f, dir))
  }

  def leftIsYounger(left: File, right: File): Boolean = {
    def time(f: File): Long = {
      val p: Path = FileSystems.getDefault.getPath(f.getAbsolutePath)
      val attr = Files.readAttributes(p, classOf[BasicFileAttributes])
      attr.lastModifiedTime().toMillis
    }

    time(left) > time(right)
  }

  def findFile(name: String, files: List[File]): Option[File] = {
    files match {
      case Nil => None
      case f :: rest => if (f.getName == name) Some(f)
      else findFile(name, rest)
    }
  }

}
