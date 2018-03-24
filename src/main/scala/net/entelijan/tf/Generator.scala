package net.entelijan.tf

import java.io.{File, PrintWriter}
import java.nio.file.{FileSystems, Files, Path}
import java.nio.file.attribute.BasicFileAttributes

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
    val l = imagesFileList(p).zipWithIndex.map {case (f, i) => p.imageText(f.getName) match {
      case None =>
        if (i == 0) 
        """    
      <li>
          <img src="%s/%s" />
          <p class="flex-caption"></p>
      </li>
      """ format (imagesDirPath(p), f.getName)
        else
        """    
      <li>
          <img class="lazy" data-src="%s/%s" />
          <p class="flex-caption"></p>
      </li>
      """ format (imagesDirPath(p), f.getName)
      
      case Some(txt) =>
        if (i == 0) 
        """    
      <li>
          <img src="%s/%s" />
          <p class="flex-caption">%s</p>
      </li>
      """ format (imagesDirPath(p), f.getName, txt)
      else
        """    
      <li>
          <img class="lazy" data-src="%s/%s" />
          <p class="flex-caption">%s</p>
      </li>
      """ format (imagesDirPath(p), f.getName, txt)

    }}
    l.mkString("\n")
  }

  def logoName(p: ImageProvider): String = {
    val f = logoFile(p)
    """%s/%s""" format (imagesDirPath(p), f.getName)
  }

  def imagesDirPath(p: ImageProvider): String = "%s/%s" format (IMAGES_DIR, p.imageFolder)

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
    d.listFiles().filter(f => acceptName(f.getName)).toList
  }

  def logoFile(p: ImageProvider): File = {
    val d = new File("src/main/web/%s" format imagesDirPath(p))
    require(d.exists(), "directory %s must exist" format d)
    val l = d.listFiles().filter(_.getName.toUpperCase().contains("LOGO")).toList
    require(l.nonEmpty, "no logo found for page '%s' in %s" format (p.id, d))
    require(l.size == 1, "more than one logo found for page '%s' in %s" format (p.id, d))
    l(0)
  }

  def htmlPageLinks(pages: List[Page]): String = {
    pages.map(htmlPageLink).mkString("\n")
  }

  def htmlPageLink(p: Page): String = {
    s"""
    <p><a href="%s">%s...</a></p>
    """ format (T.fileName(p), p.name)
  }

  def htmlContetntRight(p: Page): String = s"""
<div id="right">
    <div class="flexslider">
    <ul class="slides">
${htmlImageList(p)}  
    </ul>
    </div>		
</div>
"""

  def htmlTemplate(p: Page): String = s"""
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
  
  import MyMarkdown._

  val startPage: Page = new Page {
    def id = "index"
    def name = "start"
    
    val text: String = md("""
Aufgrund meiner 30 Jahre Erfahrung in der Fahrradbranche und den vielen Kilometern als Fahrradkurier 
bevorzuge ich für die im 'taschenfahrrad' angebotenen Räder bewährte Technik. 
Das Rad ist eine äußerst effektive und faszinierende Maschine, es erweitert die Welt, 
das Bewußtsein und das Glück seines Fahrers.

Die Implementierung von Elementen aus der motorisierten Fahrzeugtechnik sehen wir kritisch. 
Das Fahrrad soll leicht, schön und so genial einfach bleiben und ein verläßlicher Freund sein, 
sei es bei den alltäglichen Fahrten oder bei einer Reise um die Welt.
""")
    
    def htmlContent = s"""
<div id="left">
    <h1>das taschenfahrrad</h1>
    <p>stadt-, tourenräder und fahrradtaschen<p>
    <p>verkauf / werkstatt<p>
    <p>di-fr: 12:00 - 18:00</p>
    <p>sa: 9:00 - 15:00</p>
    <p id="sepa"/>
    <p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
    <p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
    <p id="sepa"/>
    <p>kontakt</p>
    <p><a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
    <p>0043 699 10431886</p>
    <p id="sepa"/>
    $text
    <p id="sepa"/>
    <p><a href="producer.html">fahrräder...</a></p>
    <p><a href="self.html">eigenbau...</a></p>
    <p><a href="accessories.html">zubehör...</a></p>
    <p><a href="team.html">team...</a></p>
    <p><a href="service.html">service...</a></p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val teamPage: Page = new Page {
    def id = "team"
    def name = "team"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="index.html">start</a> &#62; $name<p>
	<p id="sepa3"/>
	<p>
Hans Pöllhuber<br/>
Inhaber<br/>
<br/>
seit nunmehr 30 Jahren in der Branche beheimatet kennt er das Fahrradhandwerk
aus verschiedensten Perspektiven.
Absolvent HTL (Motoren und Maschinenbau) und u.a. als Fahrradkurier der ersten Stunden
hat er unglaublich viele Kilometer auf Wiens Straßen er- und überlebt.
<br/>
<br/>
Dorian Vavti<br/>
junior expert<br/>
<br/>
Absolvent HTL (Umwelttechnik) studiert Kulturtechnik und Wasserwirtschaft und verdient
sein Studium im 'taschenfahrrad'.
Begeisteter Radtourer, war zuletzt im stürmischen Island mit Rad und Zelt unterwegs
und erlernt mit Siebenmeilenstiefeln das Fahrradhandwerk.	

<br/>
<br/>
Roman Schickermüller<br/>
<br/>
ehemaliger Sportstudent der sich nach langen Jahren in der Gastronomie nun seiner Liebe für Fahrräder zuwendet und sich als Fahrradmechaniker verdienen möchte.
'Do More Of What Makes You Happy!'
  </p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPage: Page = new Page {
    def id = "self"
    def name = "eigenbau"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="index.html">start</a> &#62; $name<p>
	<p id="sepa3"/>
  <p>
Was ich am Fahrradmarkt nicht finde, baue ich unbeirrt von Moden selber.
Ab ca. 1200€ realisiere ich individuelle Fahrräder für Stadtfahrten bis Weltreise mit
verschiedenen Ausstattungsoptionen.
Basis dafür sind hochwertige CrMo Stahlrahmen folgender Firmen.</p>
<br/>
<p><a href="selfsurly.html">Surly...</a></p>
<p><a href="selfparipa.html">Paripa...</a></p>
<p><a href="selfrakete.html">Rakete...</a></p>
<p><a href="selfraco.html">Intec...</a></p>
<p><a href="selfpelago.html">Pelago...</a></p>
<p><a href="selfsalerno.html">Salerno/das taschenfahrrad...</a></p>


</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPageSurly: Page = new Page {
    def id = "selfsurly"
    def name = "surly"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
<a target="_blank" href="http://surlybikes.com/bikes/cross_check"><img src="${T.IMAGES_DIR}/selfsurly/surly-logo.jpg" /></a>
	<p id="sepa"/>
<p>
Vielseitige Designer von Fahrradrahmen aus Stahl und cleveren Fahrradkomponenten aus Bloomington, Minnesota, USA. 1. Adresse für Fahrradkuriere und 
Weltumradler und die bevorzugten Rahmen für Eigenbauten im 'taschenfahrrad'. Erprobte Qualität und dennoch leistbar. 
Während andere Hersteller noch damit beschäftigt zu kopieren arbeitet Surly schon wieder an neuen Projekten. <br/>
Bei Surly glaubt man, dass es die Taten sind, die einen Menschen definieren, nicht die Summe der Habseligkeiten, 
die Räder sind eine praktische Erweiterung seines Fahrrers. <br/>
Lagernd im 'taschenfahrrad', alle Größen:
<br/>
<br/>
Pacer 549€<br/>
Cross Check 579€<br/>
Long Haul Trucker 569€<br/>
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPageRaco: Page = new Page {
    def id = "selfraco"
    def name = "Intec"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
<a target="_blank" href="http://www.ra-co.de/"><img src="${T.IMAGES_DIR}/logos/intec-logo.png" /></a>
	<p id="sepa"/>
<p>
Die Firma Raco aus Erfurt/Deutschland läßt bei Fort/Tschechien hochwertige Stahlrahmen
fertigen, die dann bei Brandes in Gifhorn/Deutschland 2fach kunststoffbeschichtet werden.<br/>
Wir haben den F5 Rennradrahmen, F10 Crossrahmen, T6,7 Trekkingrahmen und M1
ATB/Reiseradrahmen im Programm.<br/>
Rahmengrößen: F5 52 55 58 61, F10 51 54 57 60 63,
T6 49 53 57 61 64,5 T7 42 47 52 57 und M1 42 46 50 54 58<br/>
Rahmenfarben: schwarz, tannengrün, melonengelb, rubinrot, nachtblaumetallic und
anthrazitgraumetallic<br/>
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPageParipa: Page = new Page {
    def id = "selfparipa"
    def name = "paripa"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
<a target="_blank" href="http://paripa.de/?page_id=40"><img src="${T.IMAGES_DIR}/logos/logo-paripa.jpg" /></a>
	<p id="sepa"/>
<p>
Paripa ist eine Marke der Radspannerei in Berlin. Die Rahmen werden in Europa gefertigt.
Die Idee: schnelle, sportliche und dennoch alltagstaugliche Räder für die Stadt. Die Sitzposition kann auch entspannter gestaltet werden. 
Grazil sind die Rahmen, aber dennoch robust, weil aus hochfestem Stahl. Die Rahmen sind bereits ideal vorkonfiguriert für das, was ein Rad im 
Ganzjahres einsatz dann doch braucht: Schutzbleche, Gepäckträger, Lichtverkabelung innen etc. 
Zur bewährten K-Serie gesellt sich ab Frühjahr das JWD (auf berlinerisch: „jans weit draussen“)ein Crosser für Radreisen.
<br/>
<br/>
K-Serie: 529€<br/>
JWD 599€<br/>
in der gewünschte RAL Farbe pulverbeschichtet
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPageSalerno: Page = new Page {
    def id = "selfsalerno"
    def name = "Salerno/das taschenfahrrad"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
    <a target="_blank" href="http://hartje-manufaktur.de/"><img src="${T.IMAGES_DIR}/${this.id}/logo-hartje.png" /></a>
	<p id="sepa"/>
<p>
Das Manufakturmodell Salerno gibt es auch als Rahmenset um 339€ für ein taschenfahrrad/Salerno.<br/>
3 Rahmenformen: Diamant, Mixte/Anglaise, Swiss Curve. 
12 Rahmengrößen und 20 (RAL) Farben.<br/>
So sind auch ‘weniger ist mehr’ Salernos machbar,
zB mit Nexus3, 1x8, 2x8 Schaltung. Fixe Lichtanlage oder ohne.....etc.
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPageRakete: Page = new Page {
    def id = "selfrakete"
    def name = "rakete"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
    <a target="_blank" href="http://raketerad.de"><img src="${T.IMAGES_DIR}/logos/logo_rakete.png" /></a>
	<p id="sepa"/>
<p>
Die Raketebauer aus Berlin/Prenzlauerberg haben Fahrräder mit Stahlrahmen überzeugend wiederbelebt 
und nicht nur das, auch die klassischen Rahmenformen:
Diamant, Mixte, Meral, Anglaise und Corniche.
Wir holen uns Raketenteile nach Wien und bauen uns eine 'taschenfahrrad'-Rakete:<br/>
Ein ‘Mixte’ in perlrot fand bald eine Besitzerin, lagernd ist ein ‘Corniche’ in racinggreen-metallic in Rahmenhöhe 52. 
Andere auf Anfrage 1-2 Monate Vorlaufzeit für die Rahmen.
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val selfPagePelago: Page = new Page {
    def id = "selfpelago"
    def name = "pelago"
    def htmlContent = s"""
<div id="left">
	<h1><a href="index.html">das taschenfahrrad</a></h1>
	<p><a href="${startPage.id}.html">${startPage.name}</a> &#62; <a href="${selfPage.id}.html">${selfPage.name}</a> &#62; $name<p>
	<p id="sepa3"/>
    <a target="_blank" href="https://www.pelagobicycles.com"><img src="${T.IMAGES_DIR}/selfpelago/pelago-logo.png" /></a>
	<p id="sepa"/>
<p>
Die Modelle Capri und San Sebastian gibt es auch als Rahmensets für indiviuellen Aufbau.
</p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val servicePage: Page = new Page {
    def id = "service"
    def name = "service"
    def htmlContent = s"""
<div id="left">
<h1><a href="index.html">das taschenfahrrad</a></h1>
<p><a href="index.html">start</a> &#62; $name</p>
<p id="sepa3"/>
<p>
Radservice
<p id="sepa1"/>
Ein regelmäßiges Service dient der Sicherheit und der Werterhaltung des geliebten Fahrzeuges und erspart teure Reparaturen.
<br/>
Für alle ‘taschenfahrrad’-Räder wird nach Kauf innerhalb der ersten 3 Monate ein Gratis/Garantie-Service angeboten und innerhalb der nächsten 3 Jahre gilt ein Pauschalpreis von maximal 49€. Ansonsten wird der tatsächliche Arbeitsaufwand berechnet nach Kostenvoranschlag und Leistungsumfang … 1€ pro Minute.
Wir empfehlen Radservice immer dort machen zu lassen, wo das Rad gekauft wurde. Nur dort sind immer alle Ersatzteile verfügbar, ausgenommen Tausch gängiger Verschleißteile (Reifen, Schlauch, Bremsgummis…).
<br/>
Bitte immer Termin ausmachen, sonst platzt unsere Werkstatt. Das Rad ist in der Regel immer am darauffolgenden Tag abholbereit. Der Kunde wird nach Fertigstellung per sms oder mail benachrichtigt.
<p id="sepa1"/>
Kleinere Reparaturen werden auch ‘en passant’ erledigt.
</p>
<p id="sepa3"/>
<p><a href="serviceChecklist.html">service checklist...</a></p>
</div>
${T.htmlContetntRight(this)}  
  """
  }

  val serviceChecklistPage: Page = new Page {
    def id = "serviceChecklist"
    def name = "service checklist"
    def htmlContent = s"""
<div id="left-checklist">
<h1><a href="index.html">das taschenfahrrad</a></h1>
<p><a href="index.html">start</a> &#62; <a href="service.html">service</a> &#62; $name</p>
<p id="sepa3"/>
<p>
Checkliste Service:
<br/>
<br/>
Sichtkontrolle
-Rahmen, Gabel auf Beschädigungen, Korrosion, Risse.
<br/>
<br/>
Kontrolle der ..<br/>
-Montagen, Vorbau, Sattelstütze auf festen Sitz, ggf. fetten.<br/>
-Lagereinstellungen, Naben- und Steuerlager überprüfen, ggf. fetten,
Achsgewinde und Schnellspanner fetten.<br/>
-Einstellungen von Schaltnaben überprüfen ggf. nachjustieren,
Achssitz überprüfen, Kette spannen.<br/>
-Laufräder auf Rundlauf, Speichenspannung, Einbaulage, Mittigkeit, Befestigung,
ggf. nachzentrieren, Austausch von beschädigten/verschlissenen Reifen.<br/>
-Verschleißteile und ggf. Austausch von Bremsbacken, Kette, Bowdenzüge, Seile.<br/>
-Einstellungen von Bremsen, Schaltwerk, Umwerfer, ggf. reinigen, bewegliche Teile,
Gelenkverbindungen ölen und nachjustieren.<br/>
-Anbauteile, Schutzbleche, Gepäckträger, Ständer, Lichthalter, Seitenläufer auf festen Sitz.
<br/>
<br/>

Kontrolle von<br/>
-Tretlager auf Spiel.<br/>
-Kurbel-, Kettenblatt-, Pedalverschraubung auf festen Sitz, ggf. fetten.<br/>
-Lenker, Vorbau, Griffe, Bremshebel, Schalthebel auf Einstellung, Funktion und Befestigung.<br/>
-Lichtanlage auf Funktion, Befestigung, Kabelverlegung<br/>
-STVO- Ausstattung,
<br/>
<br/>
PROBEFAHRT</p>
</div>
  """
  }

  val accessoriesPage: Page = new Page {
    def id = "accessories"
    def name = "zubehör"
    def htmlContent = s"""
<div id="left">
<h1><a href="index.html">das taschenfahrrad</a></h1>
<p><a href="index.html">start</a> &#62; $name</p>
<p id="sepa3"/>
<p>
'das taschenfahrrad' führt ein reichhaltiges Angebot
an ausgewähltem Zubehör für Stadt- Tourenradler,
z.B. :
</p>
<p id="sepa"/>
<p>Fahrradtaschen von Ortlieb, Vaude, Abus...</p>
<p>Ledersättel von Brooks, Lepper, Yak ...</p>
<p>Gepäckträger von Tubus, Racktime, Steco...</p>
<p>Helme von Bern, Nutcase, Abus....</p>
<p>Lichter von Knog, Infini, Axa, Reelight.....</p>
<p>
und  vieles mehr.     
</p>       
<p id="sepa"/>
            
			<p><a target="_blank" href="http://www.ortlieb.com/p-liste.php?ptyp=radtasche&lang=de">Ortlieb...</a></p>
			<p><a target="_blank" href="http://www.bernunlimited.com/">Bern...</a></p>
			<p><a target="_blank" href="http://www.tubus.com/">Tubus...</a></p>
			<p><a target="_blank" href="http://www.racktime.com/">Racktime...</a></p>
			<p><a target="_blank" href="http://www.brooksengland.com/">Brooks...</a></p>
			<p><a target="_blank" href="http://www.infini.tw/">Infini...</a></p>
			<p><a target="_blank" href="http://www.sellebassano.com/">selle bassano...</a></p>
			<p><a target="_blank" href="http://www.abus.com/at/Sicherheit-Unterwegs/Fahrraeder">Abus...</a></p>
			<p><a target="_blank" href="http://www.basil.nl/">Basil...</a></p>
    	
		</div>
${T.htmlContetntRight(this)}  
  """
  }

  val pages: List[Page] = List(startPage, accessoriesPage, teamPage, servicePage, serviceChecklistPage, selfPage, selfPageSurly, selfPageRaco, selfPageParipa, selfPageRakete, selfPagePelago, selfPageSalerno)

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
    new FileOutputStream(newFile) getChannel () transferFrom (
      new FileInputStream(f).getChannel, 0, Long.MaxValue)
    println("copied %s to %s" format (f, dir))
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
