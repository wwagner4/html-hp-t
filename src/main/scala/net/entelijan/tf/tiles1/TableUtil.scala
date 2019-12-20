package net.entelijan.tf.tiles1

import java.nio.file.Path

object TableUtil {

  def htmlTable(pageId: String, baseDir: Path, pageDir: String, rows: Int, cols: Int): String = {
    val images = FilesToUrl.urls(baseDir, pageDir)
    val table = TableCreator.createTable(pageId, images, rows, cols)
    TableFormater.ftable(table)
  }

  def glideTable(baseDir: Path, pageDir: String): String = {
    val images = FilesToUrl.urls(baseDir, pageDir)
    TableFormater.fglide(images)
  }

}
