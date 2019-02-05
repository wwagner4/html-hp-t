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
    val l = imagesFileList(p).zipWithIndex.map {
      case (f, i) => p.imageText(f.getName) match {
        case None =>
          if (i == 0)
            s"""
               |<li>
               |<img src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""" stripMargin
          else
            s"""
               |<li>
               |<img class="lazy" data-src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption"></p>
               |</li>
               |""" stripMargin

        case Some(txt) =>
          if (i == 0)
            s"""
               |<li>
               |<img src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption">%s</p>
               |</li>
               |""" stripMargin
          else
            s"""
               |<li>
               |<img class="lazy" data-src="${imagesDirPath(p)}/${f.getName}" />
               |<p class="flex-caption">%s</p>
               |</li>
               |""" stripMargin

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
       |<p><a href="${T.fileName(p)}">${p.name}...</a></p>
       |""" stripMargin
  }

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

  def htmlTemplate(p: Page): String =
    s"""
       |<!DOCTYPE html>
       |<html class="no-js">
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
       |<div id="all">
       |${p.htmlContent}
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

object D {

  val startPage: Page = new Page {
    def id = "index"

    def name = "start"

    def htmlContent: String =
      s"""
         |<div id="left">
         |<h1>das taschenfahrrad</h1>
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
         |<p>Wer radlos ist, kann im taschenfahrrad eine treffende Beratung
         |und mit etwas Glück gleich das passende Rad finden.
         |Wir haben uns das Knowhow in vielen Jahren als Rennradler,
         |Reiseradler, Fahrradkurier er-fahren, wissen, wo es zwicken
         |kann und wie es sich anfühlt, wenn alles passt. Unsere visits
         |sind die vielen großartigen Räder, die wir Dank unserer Kunden
         |bauen konnten.<br>
         |Das 2010 gegründete 'taschenfahrrad' bietet Räder für alltägliche
         |Stadtfahrten und Überlandfahrten bis Weltreisen.
         |Wir bevorzugen Räder mit schlankem Stahlrahmen,
         |die sowohl komfortabel als auch robust sind, mit möglichst einfacher, lang-
         |lebiger Technik. Räder, die sich schnell, unkompliziert und
         |günstig servicieren lassen. Wenn der Markt uns das nicht bieten
         |kann, bauen wir die Räder selbst.
         |</p>
         |<p id="sepa2"/>
         |
         |
         |<p>
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
      s"""|<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; $name</p>
          |<p id="sepa3"/>
          |<p class="p1">
          |    Wir haben im Laufe der Jahre viele Marken geführt, erprobt und schätzen gelernt.
          |    Veränderungen gibt es, weil sich Bezugsquellen ändern und das
          |    Platzangebot im taschenfahrrad beschränkt ist.
          |</p>
          |<p id="sepa"/>
          |<p class="p1">
          |    Einige clicks führen jeweils zur Hersteller- oder Importeurseite
          |    für detaillierte und bebilderte Infos. Die reale Welt des
          |    taschenfahrrads ist in 1020 Leopoldsg. 28,
          |</p>
          |<p id="sepa"/>
          |<p>
          |    come and see, die Auswahl:
          |</p>
          |<p id="sepa"/>
          |
          |<table>
          |    <tbody>
          |    <tr>
          |        <td><a href="http://www.tokyobike.de" target="_blank">tokyobike</a></td>
          |        <td>classic26</td>
          |        <td>47, 53, 57, 61</td>
          |        <th class="col-prize">759€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>bisou</td>
          |        <td>42, 50, 55 alle Farben</td>
          |        <td class="col-prize">659€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/road/adventure-and-touring/touring" target="_blank">FUJI</a></td>
          |        <td>touring</td>
          |        <td>49, 52, 54, 56, 58, 61, 64 black, blue</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>feather</td>
          |        <td>singlespeed</td>
          |        <td class="col-prize">499€ statt 599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td></td>
          |        <td>Größen und Farben auf Anfrage</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.konaworld.com" target="_blank">KONA</a></td>
          |        <td>sutra</td>
          |        <td>54, 56, 58 blue</td>
          |        <td class="col-prize">1399€ statt 1499€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td colspan="2">penthouse, penthouse flat, Honky Tonk und paddy wagon auf Anfrage</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.pelagobicycles.com/" target="_blank">PELAGO</a></td>
          |        <td>Airisto Outback</td>
          |        <td>57 metallic sand</td>
          |        <td class="col-prize">1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Hanko Outback</td>
          |        <td>56 moss green</td>
          |        <td class="col-prize">1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Brooklyn3</td>
          |        <td>52 black, dapper red, helene grey</td>
          |        <td class="col-prize">825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Bristol3</td>
          |        <td>57,61 black, traffic grey</td>
          |        <td class="col-prize">825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Capri3</td>
          |        <td>47,52,57  black, salmon, turquoise</td>
          |        <td class="col-prize">995€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td colspan="2">Nexus8-Versionen jeweils auf Anfrage</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.linusbike.eu" target="_blank">LINUS</a></td>
          |        <td>scout</td>
          |        <td>46 cream, mustard</td>
          |        <td class="col-prize">499€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>dutchi</td>
          |        <td>46 black, cream, sage</td>
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>mixte</td>
          |        <td>49, 56  black, sky blue</td>
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>roadster</td>
          |        <td>59 oliv, black</td>
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://cremecycles.com/" target="_blank">CREME</a></td>
          |        <td>Caferacer Lady solo</td>
          |        <td>48 green, red</td>
          |        <td class="col-prize">749€ statt  849€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Caferacer Men solo</td>
          |        <td>50, 55, 60 blue</td>
          |        <td class="col-prize">749€ statt  849€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Caferacer Men uno</td>
          |        <td>60,5 gree</td>
          |        <td class="col-prize">599€ statt  699€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Echo solo</td>
          |        <td>55 white</td>
          |        <td class="col-prize">699€ statt  869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Echo Doppio</td>
          |        <td>57 deep blue</td>
          |        <td class="col-prize">799€ statt 1199€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Echo solo Mixte</td>
          |        <td>51 sky blue</td>
          |        <td class="col-prize">699€ statt 869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Echo Lungo</td>
          |        <td>55 dark green</td>
          |        <td class="col-prize">999€ statt 1259€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://bobbinbikes.com/" target="_blank">Bobbin</a></td>
          |        <td>Noodle</td>
          |        <td>52, 56, 60 oliv, light teal</td>
          |        <td class="col-prize">499€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.contoura.de/modelle/salerno/" target="_blank">CONTOURA</a></td>
          |        <td>Salerno</td>
          |        <td>Diamant 50, 54, 58, 66, 70</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td></td>
          |        <td>Mixte Anglaise 48, 52, 56, 60</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td></td>
          |        <td>Swiss Curve 49, 54</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.naloobikes.com/" target="_blank">NALOO</a></td>
          |        <td>Chameleon</td>
          |        <td>16“, 20“, 24“ Kinderräder</td>
          |        <td class="col-prize">ab 349€</td>
          |    </tr>
          |
          |    </tbody>
          |</table>
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
          |   <p>
          |    für spezielle Anforderungen oder weil es individueller
          |    sein soll und das gewünschte Rad am Markt
          |    so nicht zu finden ist. Wir bauen Räder
          |    mit bewährten Komponenten, die wir gut
          |    kennen und die gut aufeinander  abgestimmt
          |    sind. Wir vermeiden komplexe, wartungsintensive
          |    Komponenten und lassen uns nicht von den
          |    jeweils neuesten Trends hypnotisieren,
          |    'the proof of the pudding is in the eating'.
          |</p>
          |<p>
          |    Unsere Erfahrung, welches Rad passt und
          |    welche Komponenten ihren Job schlicht,
          |    einfach und gut machen, ist die Basis fürs
          |    Gelingen. Wir verwenden die auf allen Wegen
          |    der Erde erprobten Stahlrahmen von SURLY,
          |    die gibt es ab Rahmenhöhe 38 bis 64.
          |    Körpermaße von 150cm oder 200cm bringen
          |    uns nicht in Verlegenheit.
          |    Alle Größen und Farben lagernd,
          |    Geometriedaten siehe Herstellerseite.
          |</p>
          |<table>
          |    <tbody>
          |    <tr>
          |        <td><a href="https://surlybikes.com/bikes/" target="_blank">SURLY</a></td>
          |        <td>
          |            Long Haul Trucker, Cross Check,
          |            Pack rat, Pacer, Troll u. a.
          |        </td>
          |        <td class="col-prize">ab 1650€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://paripa.de" target="_blank">PARIPA</a></td>
          |        <td>K und meral</td>
          |        <td class="col-prize">ab 1500€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>J W D</td>
          |        <td class="col-prize">ab 1650€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>alle RAL Farben</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Lieferzeit auf Anfrage</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.bricklanebikes.co.uk/frames" target="_blank">BLB</a></td>
          |        <td>Hichhiker auf Anfrage</td>
          |        <td class="col-prize">ab 1500€</td>
          |    </tr>
          |    <tr>
          |        <td>taschenfahrrad</td>
          |        <td>only</td>
          |        <td class="col-prize">ab 850€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Schlanker CrMo- Rahmen für
          |            Stadt- & Trekkingaufbauten,
          |        </td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td>Diamant</td>
          |        <td>52, 55, 59, 62</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    <tr>
          |        <td>Mixte</td>
          |        <td>48, 55 schwarz glänzend</td>
          |        <td class="col-prize"></td>
          |    </tr>
          |    </tbody>
          |</table>
          |
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
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; $name</p>
          |<p id="sepa3"/>
          |
          |<p>
          |    In den Monaten März bis Oktober sucht das taschenfahrrad
          |    Aushilfen für Verkauf, leichtere Reparaturen und für alles,
          |    was das Geschäft in Schwung hält.
          |</p>
          |<p id="sepa"/>
          |<p>
          |    Bis 40 Std./Monat, 400€/Monat an vereinbarten Tagen,
          |    für Fahrradtechniker die einen Zusatzverdienst suchen,
          |    für Personen, die in Ausbildung zum Fahrradtechniker
          |    Praxiserfahrung erwerben wollen und für Studenten mit
          |    Interesse für Fahrradtechnik.
          |</p>
          |<p id="sepa"/>
          |<p>
          |    Bewirb dich, wir freuen uns.
          |</p>
          |
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
         |<p>
         |    Ein regelmäßiges Service dient der Sicherheit und Wert-
         |    erhaltung des geliebten Fahrzeuges und erspart spätere
         |    teure Reparaturen.
         |</p>
         |<p id="sepa"/>
         |<p>
         |    Wir machen Pannendienst und schnelle Reparaturen für
         |    alle Räder, immer wird der tatsächliche Arbeitsaufwand
         |    verrechnet 1€/min.
         |</p>
         |<p id="sepa"/>
         |<p>
         |    Für taschenfahrrad- Räder wird innerhalb der ersten 3 Monate
         |    ein Gratis/Garantie-Service angeboten und innerhalb der
         |    ersten 3 Jahre gilt max. 59€ für ein umfassendes Service.
         |    Grundsätzlich empfiehlt es sich, das Radservice dort machen
         |    zu lassen, wo das Rad gekauft wurde, denn dort sollten alle
         |    Ersatzteile verfügbar sein.
         |</p>
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
