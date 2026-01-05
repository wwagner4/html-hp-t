package net.entelijan.tf.tiles

case class TileImage(
    width: Int,
    height: Int,
    tileWidth: Int,
    tileHeight: Int,
    tiles: Seq[Tile]
)
