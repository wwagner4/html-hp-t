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
          |
          |
          |<p class="sepa4"/>
          |<h1>tokyobike</h1>
          |
          |<p>
          |ein Rad für Tokyo sollte es sein,
          |dachte Ichiro Kanai in Yanaka, einem
          |Vorort von Tokyo, Gegenpol zur hektischen
          |Verkehrswelt einer riesigen Metropole.
          |Es entstanden geometrisch schlichte, sowohl
          |optisch als auch physisch leichte Räder und
          |sind seither Inbegriff für coole, urbane Mobilität.
          |Tokyo, Sydney, New York, London, Kopenhagen
          |Berlin...... und seit 2014 in Wien im
          |'taschenfahrrad'
          |</p>
          |
          |<p class="sepa2"/>
          |<p>classic26</p>
          |<p>Rahmengrößen 47 53 57 61</p>
          |<p>
          |semi matt colours:<br>
          |willow, mustard, bordeaux, moss green,
          |ivory, ash blue
          |</p>
          |<p>900€</p>
          |
          |
          |<p class="sepa2"/>
          |<p>
          |bisou
          |</p>
          |Rahmengrößen 46 50 55
          |</p>
          |
          |<p>
          |gloss colours:<br>
          |saffron, moss green, blue jade, ivory,
          |vincent blue, willow, blue gray
          |</p>
          |<p>
          |800€
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
         |<h1>service</h1>
         |<p>
         |unser erklärtes Ziel ist es, Räder zu
         |bauen, die wartungsarm, bzw. einfach und
         |schnell zu servicieren sind und das möglichst
         |überall in der Fahrrad -Welt, sollten wir
         |nicht vor Ort sein.<br>
         |Unser Werkstatt, standortbedingt klein,
         |ist in der Saison öfters nahe am platzen.
         |Die Räder werden von uns innerhalb eines
         |Tages repariert/serviciert. Zu den
         |Öffnungszeiten, möglichst di-do 15-18
         |bringen und am nächsten Tag Nachmittag
         |abholen ist unser gewohnte Praxis.
         |Selbstverständlich machen wir Pannendienst,
         |schnelle Reparaturen und können, wenn nötig
         |an andere Radwerkstätten vermitteln.
         |</p>
         |<p>
         |Angebot für taschenfahrräder:
         |</p>
         |<p>
         |GarantieCheck nach ca. 3 Monaten/300km ..gratis<br>
         |1.Service ....39€, danach<br>
         |Jahres/Zweijahresservice ....59€<br>
         |exclusive Teile
         |</p>
         |<p>
         |Mechanikerstunde 80€<br>
         |Beispiele:<br>
         |Schlauchwechsel ab 12€<br>
         |Laufradbau 50€, Aus- & Einspeichen 75€<br>
         |Kette, Pedale, Ständer tauschen 5€<br>
         |Gepäckträgermontage 10€<br>
         |Schutzblechmontage 20€<br>
         |Felge auf Seitenschlag zentrieren 10€<br>
         |exclusive Teile.
         |</p>
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

