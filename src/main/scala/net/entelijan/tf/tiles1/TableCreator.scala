package net.entelijan.tf.tiles1

case class Cell(pageId: String, id: Int, imgIndex: Int, imagePath: String) {

  def imgId(): String = {
    s"img_${id}_${imgIndex}"
  }

  def onCklick: String = {
    s"""onclick="window.open('${pageId}Glide.html?index=${imgIndex}', '_self');""""
  }

}

case class Row(cells: List[Cell])

object TableCreator {

  def createTable(
      pageId: String,
      files: List[String],
      rows: Int,
      cols: Int
  ): List[Row] = {

    var i = 0

    def createRow(rowIndex: Int, baseFilesIndex: Int): Row = {
      var result = List.empty[Cell]
      for (colIndex <- 0.until(cols)) {
        val fileIndex = (baseFilesIndex + colIndex) % files.size
        val cell = Cell(pageId, i, fileIndex, files(fileIndex))
        result = cell :: result
        i += 1
      }
      Row(cells = result.reverse)
    }

    var result = List.empty[Row]
    var filesIndex = 0
    for (rowIndex <- 0.until(rows)) {
      val row = createRow(rowIndex, filesIndex)
      result = row :: result
      filesIndex = (filesIndex + cols) % files.size
    }
    result.reverse
  }

}
