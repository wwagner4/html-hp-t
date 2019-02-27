package net.entelijan.tf.tiles

import java.nio.file.Paths

object TilesFromDirectoryTryout extends App {

   val prjDir = Paths.get("/Users/wwagner4/prj/html-hp-t")
  //val prjDir = Paths.get("/home/wwagner4/prj/taschenfahrrad-2019/html-hp-t")
  //val prjDir = Paths.get("C:/ta30/entw1/html-hp-t")
  val indir = prjDir.resolve("src/main/web/images/index")
  println(s"indir:$indir")
  val outdir = Paths.get("target/thumbs")
  //TilesFromDirectory.create("index-tiles", indir, outdir)

  TilesFromDirectory.thumbnails(indir, outdir)

}
