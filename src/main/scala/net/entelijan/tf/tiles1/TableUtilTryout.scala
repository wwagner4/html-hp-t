package net.entelijan.tf.tiles1

import java.nio.file.Paths

object TableUtilTryout extends App {

  val baseDir = Paths.get("src/main/web/images")
  print(TableUtil.htmlTable(baseDir, "index", 3, 4))

}
