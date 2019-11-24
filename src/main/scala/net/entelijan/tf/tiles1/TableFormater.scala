package net.entelijan.tf.tiles1

object TableFormater {

  def ftable(rows: List[Row]): String = {
    def ftableCell(cell: Cell): String = {
      s"""
         |<div id="${cell.imgId()}" class="rTableCell" >
         |<img class="thumb" src="${cell.imagePath}">
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

}
