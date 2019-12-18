package net.entelijan.tf.tiles1

import java.nio.file.Paths

object TableUtilTryout extends App {

  val baseDir = Paths.get("src/main/web/common")
  print(TableUtil.glideTable(baseDir, "images/index"))

}
