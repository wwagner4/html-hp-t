package net.entelijan.tf

sealed trait Layout

case object Layout_Default extends Layout

case object Layout_Wide extends Layout

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
         |<p>Verkauf / Werkstatt<p>
         |<p>di-fr: 13-18:00 sa 9-15</p>
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |<p class="sepa2"/>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">instagram...</a></p>
         |<p class="sepa"/>
         |<p>kontakt <a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p class="sepa4"/>
         |
         |<p>
         |An vorderster Reihe unseres Angebots stehen die <b>tokyobikes</b> (Tokyo)
         |für cooles entspanntes Stadtradeln und die Räder von  <b>SURLY</b>  (Bloomington/Indiana)
         |für die Befahrung der übrigen Welt auf all ihren Wegen.
         |Darüberhinaus gibt es Rahmensets und Räder von  <b>INTEC</b>, <b>paripa</b>, <b>BLB</b> und <b>taschenfahrrad</b>'s
         |black only.
         |Aber oftmals steht das gewünschte Rad passend, fixfertig und preisgünstig bereits im
         |Geschäft.
         |</p>
         |
         |<p class="sepa4"/>
         |<p><a href="producer.html">unsere Markenräder...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="selfmade.html">unsere Eigenbauräder...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="service.html">unser Service...</a></p>
         |
         |<p class="sepa2"/>
         |<p><a href="jobs.html">unser Jobangebot...</a></p>
         |
         |""".stripMargin
    }
  }

  val producerPage: Page = new Page {
    def id = "producer"

    override def layout: Layout = Layout_Wide

    def htmlContentLeftPage: String =
      s"""|<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; fahrräder</p>
          |<p class="sepa4"/>
          |<p class="p1">
          |    Wir haben im Laufe der Jahre viele Marken geführt, erprobt und schätzen gelernt.
          |    Veränderungen gibt es, weil sich Bezugsquellen ändern und das
          |    Platzangebot im taschenfahrrad beschränkt ist.
          |</p>
          |<p class="sepa"/>
          |<p class="p1">
          |    Einige clicks führen jeweils zur Hersteller- oder Importeurseite
          |    für detaillierte und bebilderte Infos. Die reale Welt des
          |    taschenfahrrads ist in 1020 Leopoldsg. 28,
          |</p>
          |<p class="sepa3"/>
          |<table>
          |    <tbody>
          |    <tr>
          |        <td><a href="http://www.tokyobike.de" target="_blank">tokyobike</a></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">classic26</a></td>
          |        <td>47, 53, 57, 61</td>
          |        <td class="col-prize1">799€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">bisou</a></td>
          |        <td>42, 50, 55 alle Farben</td>
          |        <td class="col-prize1">659€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">classic26 LTD</a></td>
          |        <td>42, 50, 55 alle Farben</td>
          |        <td class="col-prize1">809€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">classic</a></td>
          |        <td>auf Anfrage</td>
          |        <td class="col-prize1">699€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/road/adventure-and-touring/touring" target="_blank">FUJI</a></td>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/road/adventure-and-touring/touring" target="_blank">touring</a></td>
          |        <td>49, 52, 54, 56, 58 black, blue</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/city/urban/feather/feather" target="_blank">feather</a></td>
          |        <td>singlespeed</td>
          |        <td class="col-prize1">499€ statt 599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td></td>
          |        <td>Größen und Farben auf Anfrage</td>
          |        <td class="col-prize1"></td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.konaworld.com" target="_blank">KONA</a></td>
          |        <td><a href="http://2018.konaworld.com/sutra.cfm" target="_blank">sutra</a></td>
          |        <td>54, 56, 58 blue</td>
          |        <td class="col-prize1">1399€ statt 1499€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2017.konaworld.com/penthouse.cfm" target="_blank">penthouse</a></td>
          |        <td></td>
          |        <td class="col-prize">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2017.konaworld.com/penthouse_flat.cfm" target="_blank">penthouse flat</a></td>
          |        <td></td>
          |        <td class="col-prize1">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2016.konaworld.com/honky_tonk.cfm" target="_blank">Honky Tonk</a></td>
          |        <td></td>
          |        <td class="col-prize1">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2017.konaworld.com/paddy_wagon_3.cfm" target="_blank">paddy wagon</a></td>
          |        <td></td>
          |        <td class="col-prize1">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.pelagobicycles.com/" target="_blank">PELAGO</a></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/airisto-outback.html" target="_blank">Airisto Outback</a></td>
          |        <td>57 metallic sand</td>
          |        <td class="col-prize1">1099€ statt 1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/hanko-outback.html" target="_blank">Hanko Outback</a></td>
          |        <td>56 moss green</td>
          |        <td class="col-prize">1099€ statt 1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/brooklyn.html" target="_blank">Brooklyn3</a></td>
          |        <td>52 black, dapper red, helene grey</td>
          |        <td class="col-prize1">755€ statt 825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/bristol.html" target="_blank">Bristol3</a></td>
          |        <td>57,61 black, traffic grey</td>
          |        <td class="col-prize1">755€ statt 825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/capri.html" target="_blank">Capri3</a></td>
          |        <td>47,52,57  black, salmon, turquoise</td>
          |        <td class="col-prize1">995€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td colspan="2">Nexus8-Versionen jeweils auf Anfrage</td>
          |        <td class="col-prize1">+ 170€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.linusbike.eu" target="_blank">LINUS</a></td>
          |        <td><a href="https://www.linusbike.eu/products/scout-7" target="_blank">scout</a></td>
          |        <td>46 cream, mustard</td>
          |        <td class="col-prize1">499€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.linusbike.eu/products/dutchi-3" target="_blank">dutchi</a></td>
          |        <td>46 black, cream, sage</td>
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.linusbike.eu/products/mixte-3" target="_blank">mixte</a></td>
          |        <td>49, 56  black, sky blue</td>
          |        <td class="col-prize1">599€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://cremecycles.com/" target="_blank">CREME</a></td>
          |        <td><a href="http://cremecycles.com/caferecer-lady-solo,453,de.html" target="_blank">Caferacer Lady solo</a></td>
          |        <td>48 green, red</td>
          |        <td class="col-prize1">749€ statt  849€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/caferacer-man-solo-deep-blue,62,pl.html" target="_blank">Caferacer Men solo</a></td>
          |        <td>50, 55, 60 blue</td>
          |        <td class="col-prize">749€ statt  849€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/caferacer-man-uno-classic,406,de.html" target="_blank">Caferacer Men uno</a></td>
          |        <td>60,5 gree</td>
          |        <td class="col-prize1">599€ statt  699€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-solo-white,73,pl.html" target="_blank">Echo solo</a></td>
          |        <td>55 white</td>
          |        <td class="col-prize1">699€ statt  869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-doppio-deep-blue,74,pl.html" target="_blank">Echo Doppio</a></td>
          |        <td>57 deep blue</td>
          |        <td class="col-prize1">799€ statt 1199€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-solo-mixte-sky-blue,95,pl.html" target="_blank">Echo solo Mixte</a></td>
          |        <td>51 sky blue</td>
          |        <td class="col-prize1">699€ statt 869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/echo-lungo-dark-green,70,de.html" target="_blank">Echo Lungo</a></td>
          |        <td>55 dark green</td>
          |        <td class="col-prize1">999€ statt 1259€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://bobbinbikes.com/" target="_blank">Bobbin</a></td>
          |        <td>Noodle</td>
          |        <td>52, 56, 60 oliv, light teal</td>
          |        <td class="col-prize">499€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.contoura.de/modelle/salerno/" target="_blank">CONTOURA</a></td>
          |        <td><a href="https://www.contoura.de/modelle/salerno/" target="_blank">Salerno</a></td>
          |        <td>auf Anfrage</td>
          |        <td class="col-prize1">899€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.naloobikes.com/" target="_blank">NALOO</a></td>
          |        <td><a href="https://www.naloobikes.com/" target="_blank">Chameleon</a></td>
          |        <td>16“, 20“, 24“ Kinderräder</td>
          |        <td class="col-prize1">ab 349€</td>
          |    </tr>
          |
          |    </tbody>
          |</table>
          """.stripMargin
  }

  val selfmadePage: Page = new Page {
    def id = "selfmade"

    def htmlContentLeftPage: String =
      s"""|
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; eigenbau</p>
          |<p class="sepa4"/>
          |
          |<p>
          |Wenn's 100%ig passen soll dann das sind unsere Räder, die wir leidenschaftlich gerne bauen:
          |<p class="sepa1"/>
          |
          |Die Basis sind robuste, hochwertige Stahlrahmen mit excellentem Fahrverhalten,
          |bewährte Komponenten die für die Anforderungen passen
          |und ihren Job einfach gut und lange machen, wenns sein muß, eine Weltreise lang.
          |<p class="sepa4"/>
          |</p>
          |<table>
          |    <tbody>
          |    <tr>
          |        <td></td><td></td><td>Rahmen</td><td>Komplettrad</td> 
          |    </tr>
          |    <tr>
          |        <td><a href="https://surlybikes.com/bikes/long_haul_trucker" target="_blank">SURLY</a></td>
          |        <td>LHT Long Haul Trucker</td>
          |        <td>579€</td>
          |        <td class="col-prize">ab 1650€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>DT  Disc Trucker</td>
          |        <td>649€</td>
          |        <td class="col-prize">ab 1800€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>BC Bridge Club</td>
          |        <td>599€</td>
          |        <td class="col-prize">ab 1700€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Pack Rat</td>
          |        <td>599€</td>
          |        <td class="col-prize">ab 1700€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>CC Cross Check</td>
          |        <td>599€</td>
          |        <td class="col-prize">ab 1650€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>Straggler 700 & 650</td>
          |        <td>699€</td>
          |        <td class="col-prize">ab 1800€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://paripa.de" target="_blank">paripa</a></td>
          |        <td>Kserie</td>
          |        <td>500€</td>
          |        <td class="col-prize">ab 1450€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>J W D</td>
          |        <td>600€</td>
          |        <td class="col-prize">ab 1750€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://shop.ra-co.com/de/intec-rahmen.html" target="_blank">INTEC</a></td>
          |        <td>T07</td>
          |        <td>400€</td>
          |        <td class="col-prize">ab 1200€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>T06</td>
          |        <td>400€</td>
          |        <td class="col-prize">ab 1200€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>M01</td>
          |        <td>400€</td>
          |        <td class="col-prize">ab 1200€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td>F010</td>
          |        <td>450€</td>
          |        <td class="col-prize">ab 1400€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.bricklanebikes.co.uk/2018-blb-hitchhiker-frameset-blackgrey" target="_blank">BLB</a></td>
          |        <td>Hichhiker</td>
          |        <td>499€</td>
          |        <td class="col-prize">ab 1550€</td>
          |    </tr>
          |    <tr>
          |        <td>taschenfahrrad</td>
          |        <td>diamant & mixte</td>
          |        <td>300€</td>
          |        <td class="col-prize">ab 1000€</td>
          |    </tr>
          |    </tbody>
          |</table>
          """.stripMargin
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
          |    In den Monaten März bis Oktober sucht das taschenfahrrad
          |    Aushilfen für Verkauf, leichtere Reparaturen und für alles,
          |    was das Geschäft in Schwung hält.
          |</p>
          |<p class="sepa"/>
          |<p>
          |    Bis 40 Std./Monat, 400€/Monat an vereinbarten Tagen,
          |    für Fahrradtechniker die einen Zusatzverdienst suchen,
          |    für Personen, die in Ausbildung zum Fahrradtechniker
          |    Praxiserfahrung erwerben wollen und für Studenten mit
          |    Interesse für Fahrradtechnik.
          |</p>
          |<p class="sepa"/>
          |<p>
          |    Bewirb dich, wir freuen uns.
          |</p>
          """.stripMargin
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
         |Regelmäßiges Service erspart spätere teure Reparaturen, dient der Sicherheit und vor allem der Werterhaltung des Rades.
         |<p class="sepa2"/>
         |Wir machen Pannendienst und schnelle Reparaturen für fast alle Räder, Tarif 1€/min. Reparatur und Serviceumfang immer nach vorheriger Kostenabschätzung.
         |<p class="sepa1"/>
         |Bitte Termin vereinbaren, in den Monaten April, Mai, Juni kann unsere Werkstatt sonst platzen bzw. der taschenfahrrad-Radbau nimmt uns voll in Anspruch.
         |Wir bitten das Rad zum vereinbarten Termin abzuholen 
         |(meist der darauffolgende Tag), damit Platz für andere ist, die unsere Dienste dringend benötigen.
         |Wir vermitteln auch gerne an uns befreundete Kollegen in der Branche, wenn bei uns nichts mehr geht oder 
         |empfehlen ein Service dort machen zu lassen,
         |wo das Rad gekauft wurde, wenn spezielle Ersatzteile erforderlich sind.
         |<p class="sepa1"/>
         |Für taschenfahrrad-Räder gibt es ein Erst- und Garantieservice spätestens 3 Monate nach Kauf gratis, ansonsten 39€ und in den ersten 3 Jahren gilt ein
         |garantierter Fixpreis von 59€ für ein umfassendes Service, excl. eventueller Materialkosten.
         |<p class="sepa1"/>
         |Stark verschmutzte Räder werden nach vorheriger Grobreinigung serviciert, Kosten 25€.
         |</p>
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

