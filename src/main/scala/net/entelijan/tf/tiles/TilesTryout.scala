package net.entelijan.tf.tiles

import java.nio.file.Paths

import net.entelijan.tf.imgutil.{ImgFormat, ImgFormat_PNG}

object TilesTryout {

  val cfg1 = TilesConf(
    indirs = Seq(
      "index",
      "selfmade",
      "jobs",
      "producer",
      "service",
      "0223"
    ),
    indirBase = "src/main/web/images",
    outdirBase = "target/tiles"
  )
  val cfg2 = TilesConf(
    cols = 3,
    size = 500,
    borderSize = 10,
    indirs = Seq("index"),
    indirBase = "proto/WebContent/proto03/images",
    outdirBase = "proto/WebContent/proto03/images/index"
  )

  def main(): Unit = {
    // TODO add to commandline
    tiles(cfg2)
  }

  def tiles(cfg: TilesConf): Unit = {
    cfg.indirs.foreach { nam =>
      val indir = Paths.get(s"${cfg.indirBase}/$nam")
      val outdir = Paths.get(cfg.outdirBase)
      val name = s"tiles$nam"
      TilesFromDirectory.squaredTiles(
        name,
        cfg.cols,
        cfg.size,
        cfg.borderSize,
        cfg.imgType,
        0.9,
        indir,
        outdir
      )
      println(s"Wrote $name to ${outdir.toAbsolutePath}")
    }

  }

}

case class TilesConf(
    cols: Int = 4,
    size: Int = 300,
    borderSize: Int = 5,
    imgType: ImgFormat = ImgFormat_PNG,
    indirs: Seq[String],
    indirBase: String,
    outdirBase: String
)
