package net.entelijan.tf.tiles

import java.nio.file.Paths

object TilesTryout extends App {

  val cfg1 = TilesConf(
    indirs = Seq(
      "index",
      "selfmade",
      "jobs",
      "producer",
      "service",
      "0223",
    ),
    indirBase = "src/main/web/images",
    outdirBase = "target/tiles"
  )
  val cfg2 = TilesConf(
    cols = 3,
    size = 500,
    indirs = Seq("index"),
    indirBase = "proto/WebContent/proto03/images",
    outdirBase = "target/proto03"
  )

  tiles(cfg2)

  def tiles(cfg: TilesConf): Unit = {
    cfg.indirs.foreach { nam =>
      val indir = Paths.get(s"${cfg.indirBase}/$nam")
      val outdir = Paths.get(cfg.outdirBase)
      val name = s"tiles$nam"
      TilesFromDirectory.tiles(name, cfg.cols, cfg.size, indir, outdir)
      println(s"Wrote $name to ${outdir.toAbsolutePath}")
    }

  }

}


case class TilesConf(cols: Int = 4,
                     size: Int = 300,
                     indirs: Seq[String],
                     indirBase: String,
                     outdirBase: String,
                    )
