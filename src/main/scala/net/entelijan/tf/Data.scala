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
         |<p class="sepa2" />
         |
         |<p>Verkauf Räder, Zubehör und Ersatzteile</p>
         |<p class="p2">di-fr 15-18  sa 9-15</p>
         |<p>Individuelle Beratung für Eigenbauräder nach Vereinbarung jeweils</p>
         |<p class="p2">di-fr 18-19</p>
         |
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |<p class="sepa2"/>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">
         |facebook...</a>  aktuelles</p>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">
         |instagram...</a>  aktuelle bilder</p>
         |<p class="sepa"/>
         |<p>kontakt <a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p class="sepa2"/>
         |
         |
         |<p>
         |<span style="color:SeaGreen">
         |Das Geschäft ist wieder OFFEN für Verkauf & Service.
         |Die allgemeinen Öffnungszeiten wurden etwas eingeschränkt, dafür können Beratungstermine vereinbart werden jeweils di-fr 18.00 bis 19.00.
         |Wir bemühen uns Schnellservice zu machen so gut und so weit möglich, auf jeden Fall fragen, wir können auch andere Radwerkstätten empfehlen und vermitteln.
         |</span>
         |</p>
         |<p>
         |<p class="sepa2"/>
         |<span style="color:Crimson">
         |Es gelten die Covid19-bedingten Einschränkungen:<br>
         |EINZELEINLASS<br>
         |SICHERHEITSABSTAND   mindestens 2m<br>
         |SCHUTZMASKE  ffp2 oder ffp3<br>
         |</span>
         |</p>
         |
         |
         |
         |
         |<p class="sepa2"/>
         |<p><a href="producer.html">tokyobike...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="selfmade.html">SURLY und anderes...</a></p>
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
          |ivory, willow, blue gray ... 700€
          |</p>
          |
          |<p>
          |<b>classic26</b> 47, 53, 57, 61 semi-matt colours: willow, mustard, moss green,
          |ivory, bordeaux, ash blue ... 800€
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
          |<p><a href="index.html">start</a> &#62; SURLY und anderes</p>
          |<p class="sepa4"/>
          |
          |<p>
          |<b>SURLY</b> 'serious steel bikes for people who don't take themselves too seriously'.
          |Wenn es 100%ig passen soll, vom täglichen Einsatz bis zur Weltumrundung,
          |dann vertrauen wir auf die Fahrradrahmen von SURLY Bloomington/Minnesota.
          |Seit 1998 sind sie der Maßstab in Sachen TourenRäder, würdige Nachfolger der
          |legendären Bikes von Bruce Gordon BLT (BasicLoadetTouring).
          |Uns inspirieren diese äußerst widerstandsfähigen, für ein langes Radfahrerleben
          |gebauten Rahmen. Gemeinsam mit den Kunden entstehen coole, schnelle,
          |praktische Räder. Sie helfen beim Schultern der Lasten des Alltags,
          |bringen dich rasend schnell ins Wochenende oder tragen dich weit,
          |wenn's ein muß um die Welt.
          |</p>
          |
          |<p>
          |Rahmensets:<br>
          |LongHaulTrucker 579€, DiscTrucker (old) 649€,
          |DiscTrucker (new) 649€, Straggler 699€, CrossCheck 599€,
          |Midnight special 799€, BridgeClub 599€,
          |Troll 779€ andere auf Anfrage.<br>
          |Kompletträder ab1700€-2700€<br>
          |</p>
          |
          |<p>
          |<b>Intec</b>  Fahrradrahmen produziert in der kleinen Fabrik FORT in
          |Tschechien, für Tour & Alltag. Hochvergüteter, konifizierter Stahl,
          |pulverbeschichtet und unverwüstlich.
          |</p>
          |
          |<p>
          |Rahmensets:<br>
          |T07 step through 449€, T07 diamant 449€,
          |M01 449€, F10 499€<br>
          |Kompletträder ab 1400€<br>
          |</p>
          |
          |<p>
          |<b>paripa</b>  Radspannerei/Berlin läßt auch eigene Rahmen bei FORT
          |produzieren. Sanko CrMo Stahl, doppelt konifizierte,
          |schlanke Rohre, pulverbeschichtet.
          |</p>
          |
          |<p>
          |Rahmensets:<br>
          |K Serie 499€, Komlettrad ab 1400€<br>
          |JWD 599€, Komplettrad ab 1700€<br>
          |</p>
          |
          |<p>
          |<b>taschenfahrrad</b>  diamant 52, 55, 59, 62<br>
          |CrMo Stahl, schlanke Rohre, schwarz gepulvert<br>
          |Rahmenset 299€,<br>
          |Komplettrad Stadt-/Trekking ab 1200€<br>
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

