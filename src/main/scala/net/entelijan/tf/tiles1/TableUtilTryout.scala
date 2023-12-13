package net.entelijan.tf.tiles1

import java.nio.file.Paths

object TableUtilTryout {

  def main(): Unit = {
    // TODO add to commandline
    val baseDir = Paths.get("src/main/web/common")
    print(TableUtil.htmlTable("dummy", baseDir, "images/index", 3, 5))
  }

}
