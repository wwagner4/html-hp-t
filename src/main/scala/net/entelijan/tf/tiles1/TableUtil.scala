package net.entelijan.tf.tiles1

import java.nio.file.Path

object TableUtil {

  def htmlTable(baseDir: Path, pageDir: String, rows: Int, cols: Int): String = {
    val images = FilesToUrl.urls(baseDir, pageDir)
    val table = TableCreator.createTable(images, rows, cols)
    TableFormater.ftable(table)
  }
  
}
