package net.entelijan.tf.tiles

case class Size(width: Int, height: Int) {

  def ratio: Double = math.round(300.0 * width.toDouble / height) / 300.0

}
