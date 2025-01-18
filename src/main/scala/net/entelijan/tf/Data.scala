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
         |<p>ob eine fahrradtour packbar oder unpackbar ist,
         |liegt teils am fahrrad, teils nicht.<p>
         |
         |<p class="sepa4" />
         |<p class="p2">Öffnungszeiten sa  9-15  oder Beratungstermin nach Vereinbarung</p>
         |
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">instagram...</a></p>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
         |<p><a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |
         |<p class="sepa4"/>
         |<p><a href="selfmade.html">SURLY Rahmensets...</a></p>
         |
         |<p class="sepa1"/>
         |<p><a href="service.html">Service...</a></p>
         |
         |<p class="sepa1"/>
         |<p><a href="tour.html">Touren...</a></p>
         |
         |<p class="sepa4"/>
         |<p>
         |Impressum
         |</p>
         |<p>
         |Johann Pöllhuber</br>
         |das taschenfahrrad</br>
         |Leopoldsgasse 28</br>
         |1020 Wien</br>
         |ATU44349601</br>
         |GLN 9110001383918</br>
         |GISA 26861760</br>
         |</p>         
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
          |<h1>Surly Rahmensets</h1>
          |<p>alle Größen & Farben</p>
          |
          |<p>
          |<a target="_blank" href="https://surlybikes.com/bikes/disc_trucker" >DiscTrucker 799€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/karate_monkey" >KarateMonkey 999€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/krampus" >Krampus 999€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/ogre" >Ogre 899€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/collections/bridge-club" >BridgeClub 749€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/bikes/preamble" >Preamble 599€..</a><br>
          |<a target="_blank" href="https://surlybikes.com/collections/straggler" >Straggler 799€ preis gilt bis 1.3.2026..</a><br>
          |</p>
          |
          |<p>
          |Surly legacy framesets, Größen und Farben auf Anfrage<br>
          |Straggler 699€<br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/cross_check_2000" >CrossCeck 629€..</a><br>
          |PackRat 599€<br>
          |LongHaulTrucker 579€<br>
          |<a target="_blank" href="https://surlybikes.com/bikes/legacy/pacer" >Pacer 499€..</a><br>
          |</p>
          |
          |<p>andere<br>
          |<a target="_blank" href="https://pelagobicycles.com/stavanger/" >Pelago Stavanger 999€..</a><br>
          |<a target="_blank" href="https://pelagobicycles.com/airisto-commuter/" >Pelago Airisto 549€..</a><br>
          |<a target="_blank" href="https://www.brothercycles.com/shop/frames/mr-wooden/" >Brother Cycles Mr. Wooden 799€..</a><br>
          |Pelago Capri & San Sebastian<br>
          |<a target="_blank" href="https://paripa.de/?page_id=390" >Paripa JWD, Paripa Kserie, Paripa meral in..</a><br>
          |Restgrößen & im Abverkauf 399€<br>
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

  val tourPage: Page = new Page {
    def id = "tour"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; touren</p>
          |<p class="sepa4"/>
          |<h1>Touren</h1>
          |<p>
          |endlich am Start, das Pferd war zu lang im Stall
          |und scharrt jetzt mit den Hufen, uns ist etwas
          |mulmig, das Herz pocht. Ist die Tour
          |packbar? Oder eine Schnapsidee? Wir haben die
          |Idee, unseren Traum so lange gehegt und gepflegt,
          |bis er, unser Plan, so groß und startbereit geworden
          |ist. Jetzt sollte uns jemand anstupsen, zumindest
          |verabschieden. Den gewohnten, liebgewonnenen
          |Komfort zu verlassen, wenn auch nur für eine
          |Episode, kostet mehr Überwindung als wir
          |uns eingestehen.</br>
          |Die Vorbereitung, sie begann früh, es gab so
          |vieles zu bedenken und doch, zum Schluß war
          |es überhastet, alles noch am letzten Drücker,
          |„in zwei Tagen fahren wir los und es ist noch
          |gar nichts gepackt“ (o Ton Balkan Express/
          |Jochen Mesle). Jedes gepackte Stück, jedes
          |Detail innerlich abgewogen und entschieden.
          |Lagen wir richtig? Weniger ist doch mehr
          |und zuviel wird unpackbar schwer.
          |Wobei, wir leben schon lange im Alltag mit
          |dem Fahrrad, wir wissen, mit welchen
          |Socken unsere Zehen nicht abfrieren. Und
          |vieles andere wissen wir und doch, es kann
          |vieles passieren auf dem Fahrrad.
          |</p>
          |<p>
          |Wir hörten, Radfahren ist wieder-
          |gefundene Freiheit. Wo war sie, die
          |verlorene, die jetzt auf dem Fahrrad
          |wiedergefunden werden soll? Sie war
          |nie weg, sie steckte morgendlich, abendlich,
          |zu Ferienbeginn und zu Ferienende, in der
          |rush hour, im Stau. Der Stau ist es, der uns
          |am vorankommen hindert und der löst
          |sich für uns jetzt endlich auf.
          |</p>
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
         |Wir bauen Räder, die wartungsarm sind. Möglichst einfach und schnell servicierbar, das ist unser
         |Anspruch. Wir kümmern uns um alle Räder die wir gebaut haben, inclusive der Markenräder die wir
         |geführt haben. Für andere Räder machen wir Pannendienst oder vermitteln an andere Werkstätten unseres
         |Vertauens. Unsere Werkstatt, standortbedingt klein, ist mitten in der Saison öfters nahe am platzen, deshalb bitten wir
         |um Terminvereinbarung, das sorgt auch dafür, dass die Verweildauer der Serviceräder im Geschäft kurz
         |ist.
         |</p>
         |
         |<p>
         |Serviceangebote<br>
         |<br>
         |GarantieCheck nach ca. 3 Monaten/ca 300km gratis<br>
         |1.Service 49€ nach einem Jahr<br>
         |Grundservice für Räder ab 2 Jahren 79€<br>
         |<br>
         |zusätzlich Extras nur bei Bedarf:<br>
         |Steuerlager Service od. Tausch 50€<br>
         |Nabenlagerservice 35€<br>
         |Innenlagerservice od. Tausch 50€<br>
         |Reinigung 20€<br>
         |exclusive Teile.<br>
         |Mechanikerstunde 90€<br>
         |<br>
         |Beispiele:<br>
         |Schlauchwechsel ab 15€<br>
         |Laufradbau 70€<br>
         |Aus- und Einspeichen 95€<br>
         |Kette, Pedale, Ständer tauschen 5€<br>
         |Gepäckträgermontage ab 20€<br>
         |SchutzblechFestmontage 40€<br>
         |Felge auf Seitenschlag zentrieren ab 10€<br>
         |Bremsbeläge tauschen 20€<br>
         |</p>
         |
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, selfmadePage, tourPage, servicePage)

}
