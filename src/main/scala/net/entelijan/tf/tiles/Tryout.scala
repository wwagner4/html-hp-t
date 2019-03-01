package net.entelijan.tf.tiles

import java.nio.file.Paths

object Tryout extends App {

  tiles()

  def tiles(): Unit = {
    Seq(
      "index",
      "selfmade",
      "jobs",
      "producer",
      "service",
      "0223",
    ).foreach { nam =>
      val indir = Paths.get(s"src/main/web/images/$nam")
      val outdir = Paths.get("target/tiles")

      val name = s"tiles$nam"

      TilesFromDirectory.tiles(name, 4, 300, indir, outdir)
      println(s"Wrote $name to ${outdir.toAbsolutePath}")

    }

  }

}
