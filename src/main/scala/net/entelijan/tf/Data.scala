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
         |<p class="p2">Öffnungszeiten di-fr 15-18 sa 9-15</p>
         |<p>Individuelle Beratung für Eigenbauräder nach Vereinbarung jeweils di-fr 18-19</p>
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
         |<p><a href="selfmade.html">SURLY und andere...</a></p>
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
          |entspanntes Radfahren in
          |einer hektischen Metropole sieht genau
          |so aus, in Tokyo und andernorts.
          |
          |</p>
          |<p>
          |classic26 47  53  57  61<br>
          |semi matt colours: willow, mustard, bordeaux, moss green, ivory, ash blue<br>
          |<br>
          |900€
          |</p>
          |<p>
          |bisou 46  50  55<br>
          |gloss colours: saffron, moss green, blue jade, ivory, vincent blue, willow, blue gray<br>
          |<br>
          |800€<br>
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
          |'serious steel bikes for people who don't take themselves too seriously'...
          |</p>
          |<p>
          |wenn es 100%ig passen soll, vom täglichen
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
          |Rahmensets  von 38-64 lagernd:<br>
          |<a target="_blank" href="https://surlybikes.com/bikes/cross_check" >CrossCheck 629€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/straggler" >Straggler 749€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/midnight_special" >Midnight special 849€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/bridge_club" >BridgeClub 649€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/ghost_grappler" >Grappler 879€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/disc_trucker" >DiscTrucker 799€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/pack_rat" >PackRat 599€ ...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/long_haul_trucker" >LongHaulTrucker 579€ ...</a><br>
          |Preamble 599€<br>
          |KarateMonkey 949€<br>
          |</p>
          |<p>
          |Ogre, Krampus, Wednesday, Lowside, Steamroller und Big Dummy auf Anfrage.
          |</p>
          |<p>
          |Individuelle Aufbauten ab 1800€
          |</p>
          |<p>
          |Komplettrad BridgeClub 1499€
          |</p>
          |
          |andere Rahmensets:<br>
          |pelago stavanger 1095€<br>
          |brother cycles Mr. Wooden 759€<br>
          |BLB Hitchhiker 649€<br>
          |paripa, paripa meral 549€<br>
          |paripa JWD 699€<br>
          |Intec F10 519€<br>
          |Intec T6, T7 489€<br>
          |fact taschenfahrrad 349€<br>
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
          |<h1>job</h1>
          |
          |<p>
          |Mitarbeit für Verkauf, einfache Montagen/
          |Reparaturen und allem, was den Betrieb am
          |Laufen hält, willkommen. Dies besonders in
          |den arbeitsintensiven Monaten März-Oktober.
          |Geringfügige Anstellung bis zu 40Std/Monat
          |/485€, interessant für Personen in Ausbildung
          |zum Fahrradtechniker und für Studierende
          |mit Leidenschaft und Interesse für
          |Fahrradtechnik.
          |</p>
          |<p>
          |Für Profis in Fahrradtechnik/Verkauf und
          |Leidenschaft für Tourenräder ist auch eine
          |Fixanstellung möglich.
          |</p>
          |
          |
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
         |
         |<p>
         |unser erklärtes Ziel ist es, Räder zu
         |bauen, die wartungsarm, möglichst einfach und
         |schnell zu servicieren sind und das nahezu
         |überall in der (Fahrrad)-Welt, sollten wir
         |nicht vor Ort sein.<br>
         |Unser Werkstatt, standortbedingt klein,
         |ist in der Saison öfters nahe am platzen.
         |Die Räder werden von uns innerhalb eines
         |Tages repariert/serviciert. Zu den
         |Öffnungszeiten, möglichst di-do 15-18
         |bringen und am nächsten Tag Nachmittag
         |abholen ist unser gewohnte Praxis.<br>
         |Selbstverständlich machen wir Pannendienst,
         |schnelle Reparaturen und können, wenn nötig
         |an andere Radwerkstätten vermitteln.
         |</p>
         |
         |<p>
         |Angebot für taschenfahrräder:<br>
         |<br>
         |GarantieCheck nach ca. 3 Monaten/300km gratis<br>
         |1.Service 39€, danach Jahres/Zweijahresservice 59€<br>
         |<br>
         |extras:<br>
         |<br>
         |Steuerlager Service od. Tausch 50€<br>
         |Nabenlagerservice 35€<br>
         |Innenlagerservice od. Tausch 50€<br>
         |Reinigung 20€<br>
         |<br>
         |exclusive Teile.<br>
         |<br>
         |Mechanikerstunde 80€<br>
         |<br>
         |Beispiele:<br>
         |Schlauchwechsel ab 12€<br>
         |Laufradbau 60€<br>
         |Aus- & Einspeichen 75€<br>
         |Kette, Pedale, Ständer tauschen 5€<br>
         |Gepäckträgermontage 20€<br>
         |Schutzblechmontage 30€<br>
         |Felge auf Seitenschlag zentrieren ab 10€<br>
         |<br>
         |exclusive Teile.<br>
         |
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

