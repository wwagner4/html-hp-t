package net.entelijan.tf.tiles1

object TableUtil {

  def htmlTable(images: List[String], rows: Int, cols: Int): String = {
    val table = TableCreator.createTable(images, rows, cols)
    TableFormater.ftable(table)
  }
  
}
