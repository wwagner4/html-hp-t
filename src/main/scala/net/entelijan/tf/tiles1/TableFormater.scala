package net.entelijan.tf.tiles1

object TableFormater {

  def ftable(rows: List[Row]): String = {
    def ftableRow(row: Row): String = {
      s"""
         |<div class="rTableRow">
         |${row.cells map ftableCell mkString "\n"}
         |</div>
         |""".stripMargin
    }

    def ftableCell(cell: Cell): String = {
      s"""
         |<div id="${cell.imgId()}" class="thumb rTableCell" style="background-image: url('${cell.imagePath}')"></div>
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
