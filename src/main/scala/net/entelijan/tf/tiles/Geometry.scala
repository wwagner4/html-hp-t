package net.entelijan.tf.tiles

object Geometry {

  def tiles(tileSize: Size, cols: Int)(images: Seq[String]): TileImage = {
    require(images.nonEmpty, "You need at least one image")
    val rows = math.ceil(images.size.toDouble / cols).toInt
    val tileWidth = tileSize.width
    val tileHeight = tileSize.height
    val imagesEndless = LazyList.continually(images).flatten
    val results: Seq[Seq[Tile]] =
      for (i <- 0 until rows) yield {
        for (j <- 0 until cols) yield {
          val idx = i * cols + j
          val img = imagesEndless(idx)
          Tile(img, j * tileWidth, i * tileHeight)
        }
      }
    val tiles = results.flatten
    val w = cols * tileWidth
    val h = rows * tileHeight
    TileImage(w, h, tileWidth, tileHeight, tiles)
  }


}
