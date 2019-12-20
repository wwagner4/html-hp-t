package net.entelijan.tf.tiles1

object TableFormater {

  def ftable(rows: List[Row]): String = {
    def ftableCell(cell: Cell): String = {
      s"""
         |<div id="${cell.imgId()}" class="rTableCell" >
         |<img class="thumb" src="${cell.imagePath}" ${cell.onCklick}>
         |</div>
         |""".stripMargin
    }

    def ftableRow(row: Row): String = {
      s"""
         |<div class="rTableRow">
         |${row.cells map ftableCell mkString "\n"}
         |</div>
         |""".stripMargin
    }

    val lines =
      s"""
         |<div class="rTable">
         |${rows map ftableRow mkString "\n"}
         |</div>
         |""".stripMargin.split("\n").toList
    lines
      .filter(!_.isEmpty)
      .mkString("\n")
  }

  def fglide(images: List[String]): String = {
    def ftableRow(imageUrl: String): String = {
      s"""  <li class="glide__slide">
         |    <div class="fill" style="background-image: url($imageUrl);"></div>
         |  </li>
         |""".stripMargin
    }

    val lines =
      s"""<div class="glide__track" data-glide-el="track">
         |<ul class="glide__slides">
         |${images map ftableRow mkString "\n"}
         |</ul>
         |</div>
         |""".stripMargin.split("\n").toList
    lines
      .filter(!_.isEmpty)
      .mkString("\n")
  }

}
