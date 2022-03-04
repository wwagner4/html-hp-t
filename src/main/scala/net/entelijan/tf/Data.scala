package net.entelijan.tf

sealed trait Layout

case object Layout_Default extends Layout

case object Layout_Wide extends Layout

case object Layout_Middle extends Layout

trait Page {
  def id: String

  def layout: Layout = Layout_Default

  def htmlContentLeftPage: String

}

object Data {

  val startPage: Page = new Page {
    def id = "index"

    def htmlContentLeftPage: String = {
      s"""
         |<h1>das taschenfahrrad</h1>
         |<p>stadt-, tourenräder und fahrradtaschen<p>
         |
         |<p class="sepa4" />
         |<p class="p2">Öffnungszeiten di-fr 15-18  sa 9-15</p>
         |<p>Individuelle Beratung für Eigenbauräder nach Vereinbarung jeweils
         |di-fr 18-19</p>
         |
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |
         |
         |<p class="sepa2"/>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">
         |instagram...</a></p>
         |<p class="sepa"/>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">
         |facebook...</a></p>
         |
         |
         |
         |<p class="sepa2"/>
         |<p><a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p class="sepa2"/>
         |
         |<p>
         |Es gelten die aktuellen Covid19
         |bedingten Einschränkungen.
         |</p>
         |
         |
         |<p class="sepa4"/>
         |<p><a href="selfmade.html">SURLY...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="producer.html">tokyobike...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="service.html">service...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="jobs.html">jobs...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="https://firmen.wko.at/Web/DetailsKontakt.aspx?FirmaID=3fbab856-76f3-41ab-849f-643b215a4db8&Name=Johann%20P%C3%B6llhuber&Standort=Wien%20(Bundesland)"  target="_blank">
         |impressum...</a></p>
         |
         |""".stripMargin
    }
  }

  val producerPage: Page = new Page {
    def id = "producer"

    override def layout: Layout = Layout_Default

    def htmlContentLeftPage: String =
      s"""|<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; tokyobike</p>
          |<p class="sepa4"/>
          |
          |<p>
          |<b>tokyobikes</b>  entstanden 2002 in Yanaka/Tokyo und sind inzwischen in vielen Metropolen der
          |Welt zu finden. Für diese leichtgewichtigen, unkomplizierten Räder sind smart, pfiffig,
          |simple, cool, einfach und schön, zutreffende Attribute. Seit einigen Jahren verschönern
          |sie auch in Wien das urbane mobile Leben. Sie sind die bevorzugte Marke im
          |taschenfahrrad, normal sind vor der Kirschblüte die Lager der tokyobike-shop's voll,
          |heuer nicht so, coranabedingt hinkt die Produktion der großen Nachfrage hinterher, sie
          |kommen verspätet, aber ganz bestimmt, im Juni.
          |</p>
          |
          |<p>
          |<b>bisou</b> 42, 50, 55 gloss colours: saffron, moss green, blue jade, vincent blue,
          |ivory, willow, blue gray ... 750€
          |</p>
          |
          |<p>
          |<b>classic26</b> 47, 53, 57, 61 semi-matt colours: willow, mustard, moss green,
          |ivory, bordeaux, ash blue ... 850€
          |</p>
          |
          |<p>
          |<b>little tokyobike</b> auf Anfrage ... 300€
          |</p>
          |
          |<p>
          |Andere Marken im <b>SALE</b><br>
          |Fuji  Touring 52 blue, 58 black um 899€, Fuji Feather 499€ statt 599€
          |Kona Sutra 54, 56 blue 1399€ statt 1599€, Penthouse, Honky Tonk, Paddy Wagon
          |Pelago Bristol, Brooklyn 699€ statt 775€, Capri 899€ statt 995€, Airisto Outback
          |Creme caferacer men 49, 60, Echo solo white 55, Echo Doppio blue 57
          |Linus Dutchi 46 black 499€, Roadster 59 black 499€,
          |Bobbin Noodle 60 moody blue 499€, Birdie8 599€,
          |Viva, Breezer, Marin auf Anfrage
          |</p>
          |
          """.stripMargin
  }

  val selfmadePage: Page = new Page {
    def id = "selfmade"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; SURLY</p>
          |
          |<p class="sepa4"/>
          |<h1>SURLY</h1>
          |
          |<p>
          |'serious steel bikes for people who don't take themselves too seriously...'
          |</p>
          |
          |<p>
          |wenn es 100ig passen soll, vom täglichen
          |Einsatz bis zur Weltreise, dann vertrauen
          |wir auf Fahrradrahmen von SURLY.
          |Seit 1998 sind sie Maßstab in Sachen
          |Tourenräder. Sie sind uns eine Inspiration
          |für vieles und gemeinsam mit unseren
          |Kunden entstehen coole Räder, die
          |schnell und praktisch sind. Sie helfen
          |beim Schultern der Lasten des Alltags,
          |bringen dich schnell ins Wochenende,
          |über die Berge und weit hinaus,
          |wenn's sein muss, rund um unseren
          |wunderbaren Planeten.
          |</p>
          |
          |<p>
          |Rahmensets:<br>
          |CrossCheck 629€<br>
          |Straggler 699€<br>
          |Midnight special 849€<br>
          |BridgeClub 599€<br>
          |GhostGrappler 839€<br>
          |DiscTrucker 799€<br>
          |PackRat 599€<br>
          |LongHaulTrucker 579€<br>
          |Preamble 549€<br>
          |</p>
          |
          |<p>
          |Rahmengrößen von 38 – 64
          |oder xs s m l xl
          |</p>
          |
          |<p>
          |Wir bauen Kompletträder ab
          |1600€ bis 2900€
          |</p>
          |
          |""".stripMargin
  }

  val jobsPage: Page = new Page {
    def id = "jobs"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; jobs</p>
          |<p class="sepa4"/>
          |
          |<p>
          |Es gibt die Möglichkeit als Aushilfe für Verkauf, Montagen und einfachen Reparaturen, zu
          |arbeiten in den besonders intensiven Monaten März bis Oktober, 40 Stunden / Monat, 440€.
          |Interessant für Personen in Ausbildung zum Fahrradtechniker für den Erwerb von
          |Praxiserfahrung, für Studenten mit Leidenschaft für Fahrradtechnik und für Fahrradboten
          |die mehr als ihr eigenes Dienstfahrrad im Griff haben. Für erfahrene Profis in
          |Fahrradtechnik und Verkauf, mit Leidenschaft für Tourenräder, ist auch eine
          |Fixanstellung möglich.
          |</p>
          |<p>
          |Bewirb dich, wir freuen uns.
          |</p>
          |""".stripMargin
  }

  val servicePage: Page = new Page {

    def id = "service"

    def htmlContentLeftPage: String =
      s"""
         |<h1><a href="index.html">das taschenfahrrad</a></h1>
         |<p><a href="index.html">start</a> &#62; service</p>
         |<p class="sepa4"/>
         |
         |<p>
         |Regelmäßiges Service und Reinigung dient der Sicherheit und Werterhaltung des
         |Rades ... eine Binsenweisheit.
         |Wir machen Pannendienst und schnelle Reparaturen im Rahmen unserer verfügbaren
         |Kapazitäten. Das Geschäft ist klein, in der Hauptsaison kann es vorkommen, dass die
         |Werkstatt platzt, wir nur mit Termin Räder annehmen oder andere RadWerkstätten,
         |befreundeter Kollegen empfehlen beziehungsweise vermitteln. Sinnvoll und kostengünstig
         |ist es, ein Radservice dort machen zu lassen wo das Rad gekauft wurde.
         |</p>
         |
         |<p>
         |Wir machen bei Reparaturannahme einen Kostenvoranschlag der fix eingehalten wird,
         |ausgenommen Unvorhergesehenes, dann halten wir Rücksprache. taschenfahrrad-Räder werden
         |bevorzugt, für die wird immer ein Platz leergeräumt, für die gilt ein Gratis /
         |Garantieservice nach ca. 3 Monaten,
         |</p>
         |
         |<p>
         |1. Service nach einem Jahr ... 39€, weitere ... 59€ / Jahresservice, erst wenn der
         |Arbeitaufwand die normale Servicezeit 3/4 Stunde übersteigt, gilt der
         |Stundentarif 79€ / Stunde, jeweils exclusive Verschleißteile.
         |Stark verschmutzte Räder werden vorher gereinigt ... 20€.
         |</p>
         |
         |<p>
         |Unser Ziel ist es Räder zu bauen, die einfach und schnell zu servicieren sind.
         |</p>
         |
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

