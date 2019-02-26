package net.entelijan.tf.tiles

import scala.collection.immutable

object Geometry {

  def tiles(tilesWidth: Int, cols: Int)(images: Seq[Image]): TileImage = {
    def imagesFormatted: String =
      images
        .map(i => s"${i.id} ${i.size} ${i.size.ratio}")
        .mkString("\n")

    def allSameRatio: Boolean =
      images.forall(_.size.ratio == images.head.size.ratio)

    require(allSameRatio, s"All images must be of the same ratio: \n$imagesFormatted")
    require(images.nonEmpty, "You need at least one image")
    val img = images.head
    val rows = math.ceil(images.size.toDouble / cols).toInt
    val tileWidth = (tilesWidth.toDouble / cols).toInt
    val tileHeight = (tileWidth / img.size.ratio).toInt
    val imagesEndless = Stream.continually(images).flatten
    val results: Seq[Seq[Tile]] = for (i <- 0 until rows) yield {
      for (j <- 0 until cols) yield {
        val idx = i * rows + j
        val img = imagesEndless(idx)
        val scale = ScaleFactors(tileWidth.toDouble / img.size.width, tileHeight.toDouble / img.size.height)
        Tile(img.id, scale, j * tileWidth, i * tileHeight)
      }
    }
    val tiles = results.flatten
    val w = cols * tileWidth
    val h = rows * tileHeight
    TileImage(w, h, tileWidth, tileHeight, tiles)
  }


}
