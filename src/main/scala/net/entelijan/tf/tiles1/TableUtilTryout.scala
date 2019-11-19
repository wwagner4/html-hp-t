package net.entelijan.tf.tiles1

object TableUtilTryout extends App {

  val images = List(
    "images/img1.jpg",
    "images/img2.jpg",
    "images/img3.jpg",
    "images/img4.jpg",
    "images/img5.jpg",
  )
  print(TableUtil.htmlTable(images, 3, 4))

}
