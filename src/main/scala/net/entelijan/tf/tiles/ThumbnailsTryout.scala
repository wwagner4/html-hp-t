package net.entelijan.tf.tiles

import java.nio.file.Paths

object ThumbnailsTryout extends App {

  val indir = Paths.get("src/main/web/images/index")
  println(s"indir:${indir.toAbsolutePath}")
  val outdir = Paths.get("target/thumbs")
  //TilesFromDirectory.create("index-tiles", indir, outdir)

  TilesFromDirectory.thumbnails(indir, outdir)
  println(s"thumbs in ${outdir.toAbsolutePath}")

}
