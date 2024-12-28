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
         |<p>tourenräder und fahrradtaschen<p>
         |
         |<p class="sepa4" />
         |<p class="p2">Öffnungszeiten sa 9-15</p>
         |<p>Individuelle Beratung für Eigenbauräder sowie Servicetermine nach Vereinbarung</p>
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">instagram...</a></p>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
         |<p><a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p class="sepa4"/>
         |<p><a href="selfmade.html">SURLY und andere...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="sale.html">Abverkaufsräder reduziert...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="service.html">Service...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="jobs.html">Touren...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="https://firmen.wko.at/Web/DetailsKontakt.aspx?FirmaID=3fbab856-76f3-41ab-849f-643b215a4db8&Name=Johann%20P%C3%B6llhuber&Standort=Wien%20(Bundesland)"  target="_blank">
         |Impressum...</a></p>
         |
         |""".stripMargin
    }
  }

  val selfmadePage: Page = new Page {
    def id = "selfmade"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; SURLY</p>
          |
          |<p class="sepa4"/>
          |<h1>SURLY framesets</h1>
          |
          |<p>
          |&#39;serious steel bikes for people who don&#39;t take themselves too seriously&#39;, gemeinsam mit unseren
          |Kunden entstehen Räder, die schnell und praktisch sind. Für Fahrten im Alltag, ins Wochenende
          |und eventuell rund um unseren wunderbaren Planeten.
          |</p>
          |<br>
          |
          |<p>
          |<a target="_blank" href="https://surlybikes.com/bikes/straggler" >Straggler 699€ black, chlorine dream, cold blue...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/preamble" >Preamble 599€ throfrost white, black, skyrim blue, best buds green...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/midnight_special" >Midnight special 799€ black, fools gold, metallic lilac...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/bridge_club" >BridgeClub 749€ majestic moss, grandma's lipstic, trevor's closet black, whipped butter...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/disc_trucker" >DiscTrucker 799€ black, pea lime soup...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/ogre" >Ogre 799€ fermented plum...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/grappler" >Grappler 849€ subterranean homesick blue, purple dust bunny...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/karate_monkey" >KarateMonkey 999€ peach salmon sundae, organic eggplant, lost at sea, snow mold, black...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/krampus" >Krampus 999€ prickly pear, nose drip curry, blue oyster coat, chester copperpot...</a><br>
          |alle Rahmensets 38-64 bzw XS-XL<br>
          |</p>
          |
          |<p>
          |Surly Legacy framesets<br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/cross_check_2000" >CrossCheck 629€ all sizes, black, bluegreengray...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/long_haul_trucker" >LongHaulTrucker only 42, 46, 56 579€ black...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/pack_rat" >PackRat only 52,54,56,58 599€ gray haze...</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/pacer" >Pacer only 42,46,50,54,58, 499€...</a><br>
          |</p>
          |
          |<p>Pelago framesets<br>
          |<a target="_blank" href="https://pelagobicycles.com/stavanger/" >Stavanger 1095€, gray terra, lilametall...</a><br>
          |<a target="_blank" href="https://pelagobicycles.com/airisto-commuter/" >Airisto 549€ , charcoal...</a><br>
          |San Sebastian, Capri auf Anfrage<br>
          |</p>
          |
          |<p>BROTHER cycles framesets<br>
          |<a target="_blank" href="https://www.brothercycles.com/shop/frames/mr-wooden/" >Mr. Wooden XS-XL 789€ green...</a><br>
          |</p>
          |
          |<p>andere<br>
          |<a target="_blank" href="https://www.bricklanebikes.co.uk/blb-hitchhiker-29er-drop-bar-gravel-racer" >BLB Hitchhiker 57 499€...</a><br>
          |<a target="_blank" href="https://paripa.de/?page_id=390" >paripa JWD 50,54 499€...</a><br>
          |<a target="_blank" href="https://paripa.de" >Intec, paripa Kserie, paripa meral auf Anfrage 499€...</a><br>
          |</p>
          |""".stripMargin
  }

  val salePage: Page = new Page {
    def id = "sale"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; Abverkauf</p>
          |
          |<p class="sepa4"/>
          |<h1>Lagerräumung, Abverkauf</h1>
          |<p>in Arbeit</p>
          |""".stripMargin
  }

  val jobsPage: Page = new Page {
    def id = "jobs"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; touren</p>
          |<p class="sepa4"/>
          |<h1>Touren</h1>
          |
          |
          |
          |""".stripMargin
  }

  val servicePage: Page = new Page {

    def id = "service"

    def htmlContentLeftPage: String =
      s"""
         |<h1><a href="index.html">das taschenfahrrad</a></h1>
         |<p><a href="index.html">start</a> &#62; Service</p>
         |<p class="sepa4"/>
         |<h1>Service</h1>
         |
         |<p>
         |unser erklärtes Ziel ist es, Räder zu
         |bauen, die wartungsarm, möglichst einfach und
         |schnell zu servicieren sind und das nahez
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

  val pages: List[Page] = List(startPage, selfmadePage, salePage, jobsPage, servicePage)

}

