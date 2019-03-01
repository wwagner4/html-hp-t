package net.entelijan.tf.tiles

import java.nio.file.Paths

object Tryout extends App {

  tiles()

  def tiles(): Unit = {
    val indir = Paths.get("target/thumbs")
    val outdir = Paths.get("target/tiles")

    val name = "tiles001"

    TilesFromDirectory.tiles(name, 3, Size(208, 210), indir, outdir)
    println("Wrote to " + outdir.toAbsolutePath)

  }

  def thumbnails(): Unit = {
    val indir = Paths.get("src/main/web/images/index")
    println(s"indir:${indir.toAbsolutePath}")
    val outdir = Paths.get("target/thumbs")
    //TilesFromDirectory.create("index-tiles", indir, outdir)

    TilesFromDirectory.thumbnails(Size(200, 100), indir, outdir)
    println(s"thumbs in ${outdir.toAbsolutePath}")
  }

}
