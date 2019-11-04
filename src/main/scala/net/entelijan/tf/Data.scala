package net.entelijan.tf

trait ImageProvider {
  def id: String

  def imageFolder: String = id
}

trait Page extends ImageProvider {
  def name: String

  def htmlContent(templ: Templ): String

  /**
    * Defines a text to an image.
    * Filename of the image can be used to identify the image
    */
  def imageText(fnam: String): Option[String] = None
}

trait OverviewPage extends Page {
  def pages: List[Page]
}

object Data {

  val startPage: Page = new Page {
    def id = "index"

    def name = "start"

    def htmlContent(templ: Templ): String =
      s"""
         |<div id="left">
         |<h1>das taschenfahrrad</h1>
         |<p>stadt-, tourenräder und fahrradtaschen<p>
         |<p class="sepa2" />
         |<p>Verkauf / Werkstatt<p>
         |<p>di-fr: 13-18:00 sa 9-15</p>
         |<p><a target="_blank" href="http://www.openstreetmap.org/?lat=48.218173500000006&amp;lon=16.377131&amp;zoom=17&amp;layers=M&amp;mlat=48.21819&amp;mlon=16.37711">Leopoldsgasse 28 1020 Wien...</a></p>
         |<p><a target="_blank" href="https://www.facebook.com/das-taschenfahrrad-108130579232304">facebook...</a></p>
         |<p><a target="_blank" href="https://www.instagram.com/taschenfahrrad/">instagram...</a></p>
         |<p class="sepa"/>
         |<p>kontakt <a href="mailto:hans.poellhuber@chello.at">hans.poellhuber@chello.at</a></p>
         |<p>0043 699 1043 1886</p>
         |<p class="sepa2"/>
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
         |<p class="sepa2"/>
         |
         |
         |<p>
         |Wir lieben schnelle Räder, die auch praktisch sind und wir wollen
         |schöne Räder, weil wir gerne und mit Stolz radfahren.
         |</p>
         |
         |<p class="sepa2"/>
         |<p><a href="producer.html">unsere Markenräder...</a></p>
         |
         |<p class="sepa"/>
         |<p><a href="selfmade.html">unsere Eigenbauräder...</a></p>
         |
         |<p class="sepa"/>
         |<p><a href="service.html">unser Service...</a></p>
         |
         |<p class="sepa"/>
         |<p><a href="jobs.html">unser Jobangebot...</a></p>
         |
         |</div>
         |${templ.htmlContetntRight(this)}
         |""".stripMargin
  }


  val producerPage: Page = new Page {
    def id = "producer"

    def name = "fahrräder"

    def htmlContent(templ: Templ): String =
      s"""|<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; $name</p>
          |<p class="sepa3"/>
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
          |<p class="sepa"/>
          |<p>
          |    come and see, die Auswahl:
          |</p>
          |<p class="sepa"/>
          |
          |<table>
          |    <tbody>
          |    <tr>
          |        <td><a href="http://www.tokyobike.de" target="_blank">tokyobike</a></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">classic26</a></td>
          |        <td>47, 53, 57, 61</td>
          |        <td class="col-prize">789€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.tokyobike.de/?area=bikes" target="_blank">bisou</a></td>
          |        <td>42, 50, 55 alle Farben</td>
          |        <td class="col-prize">659€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/road/adventure-and-touring/touring" target="_blank">FUJI</a></td>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/road/adventure-and-touring/touring" target="_blank">touring</a></td>
          |        <td>49, 52, 54, 56, 58, 61, 64 black, blue</td>
          |        <td class="col-prize">899€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://www.fujibikes.com/usa/bikes/city/urban/feather/feather" target="_blank">feather</a></td>
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
          |        <td><a href="http://2018.konaworld.com/sutra.cfm" target="_blank">sutra</a></td>
          |        <td>54, 56, 58 blue</td>
          |        <td class="col-prize">1399€ statt 1499€</td>
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
          |        <td class="col-prize">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2016.konaworld.com/honky_tonk.cfm" target="_blank">Honky Tonk</a></td>
          |        <td></td>
          |        <td class="col-prize">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://2017.konaworld.com/paddy_wagon_3.cfm" target="_blank">paddy wagon</a></td>
          |        <td></td>
          |        <td class="col-prize">auf Anfrage</td>
          |    </tr>
          |    <tr>
          |        <td><a href="https://www.pelagobicycles.com/" target="_blank">PELAGO</a></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/airisto-outback.html" target="_blank">Airisto Outback</a></td>
          |        <td>57 metallic sand</td>
          |        <td class="col-prize">1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/hanko-outback.html" target="_blank">Hanko Outback</a></td>
          |        <td>56 moss green</td>
          |        <td class="col-prize">1335€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/brooklyn.html" target="_blank">Brooklyn3</a></td>
          |        <td>52 black, dapper red, helene grey</td>
          |        <td class="col-prize">825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/bristol.html" target="_blank">Bristol3</a></td>
          |        <td>57,61 black, traffic grey</td>
          |        <td class="col-prize">825€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.pelagobicycles.com/bicycles/capri.html" target="_blank">Capri3</a></td>
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
          |        <td><a href="https://www.linusbike.eu/products/scout-7" target="_blank">scout</a></td>
          |        <td>46 cream, mustard</td>
          |        <td class="col-prize">499€</td>
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
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="https://www.linusbike.eu/products/roadster3" target="_blank">roadster</a></td>
          |        <td>59 oliv, black</td>
          |        <td class="col-prize">599€</td>
          |    </tr>
          |    <tr>
          |        <td><a href="http://cremecycles.com/" target="_blank">CREME</a></td>
          |        <td><a href="http://cremecycles.com/caferecer-lady-solo,453,de.html" target="_blank">Caferacer Lady solo</a></td>
          |        <td>48 green, red</td>
          |        <td class="col-prize">749€ statt  849€</td>
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
          |        <td class="col-prize">599€ statt  699€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-solo-white,73,pl.html" target="_blank">Echo solo</a></td>
          |        <td>55 white</td>
          |        <td class="col-prize">699€ statt  869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-doppio-deep-blue,74,pl.html" target="_blank">Echo Doppio</a></td>
          |        <td>57 deep blue</td>
          |        <td class="col-prize">799€ statt 1199€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/de/echo-solo-mixte-sky-blue,95,pl.html" target="_blank">Echo solo Mixte</a></td>
          |        <td>51 sky blue</td>
          |        <td class="col-prize">699€ statt 869€</td>
          |    </tr>
          |    <tr>
          |        <td></td>
          |        <td><a href="http://cremecycles.com/echo-lungo-dark-green,70,de.html" target="_blank">Echo Lungo</a></td>
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
          |        <td><a href="https://www.contoura.de/modelle/salerno/" target="_blank">Salerno</a></td>
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
          |        <td><a href="https://www.naloobikes.com/" target="_blank">Chameleon</a></td>
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

    def htmlContent(templ: Templ): String =
      s"""|<div id="left">
          |   <h1><a href="index.html">das taschenfahrrad</a></h1>
          |   <p><a href="index.html">start</a> &#62; $name</p>
          |   <p class="sepa3"/>
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
          |        <td><a href="https://surlybikes.com/bikes/long_haul_trucker" target="_blank">SURLY</a></td>
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
          |        <td><a href="https://www.bricklanebikes.co.uk/2018-blb-hitchhiker-frameset-blackgrey" target="_blank">BLB</a></td>
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
          |${templ.htmlContetntRight(this)}
          """.stripMargin
  }

  val jobsPage: Page = new Page {
    def id = "jobs"

    def name = "jobs"

    def htmlContent(templ: Templ): String =
      s"""|<div id="left">
          |<h1><a href="index.html">das taschenfahrrad</a></h1>
          |<p><a href="index.html">start</a> &#62; $name</p>
          |<p class="sepa3"/>
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
          |
          |
          |</div>
          |${templ.htmlContetntRight(this)}
          """.stripMargin
  }

  val servicePage: Page = new Page {
    def id = "service"

    def name = "service"

    def htmlContent(templ: Templ): String =
      s"""
         |<div id="left">
         |<h1><a href="index.html">das taschenfahrrad</a></h1>
         |<p><a href="index.html">start</a> &#62; $name</p>
         |<p class="sepa3"/>
         |
         |<p>
         |    Ein regelmäßiges Service dient der Sicherheit und Wert-
         |    erhaltung des geliebten Fahrzeuges und erspart spätere
         |    teure Reparaturen.
         |</p>
         |<p class="sepa"/>
         |<p>
         |    Wir machen Pannendienst und schnelle Reparaturen für
         |    alle Räder, immer wird der tatsächliche Arbeitsaufwand
         |    verrechnet 1€/min.
         |</p>
         |<p class="sepa"/>
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
         |${templ.htmlContetntRight(this)}
         |""".stripMargin
  }

  val pages: List[Page] = List(startPage, producerPage, selfmadePage, jobsPage, servicePage)

}

