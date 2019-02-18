package net.entelijan.tf.tiles

import java.nio.file.Paths

object TilesFromDirectoryTryout extends App {

  val indir = Paths.get("/home/wwagner4/prj/taschenfahrrad-2019/html-hp-t/src/main/web/images/index")
  val outdir = Paths.get("target/tiles")
  TilesFromDirectory.create("index-tiles", indir, outdir)


}
