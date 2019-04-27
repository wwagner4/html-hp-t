package net.entelijan.tf.tiles

import org.scalatest.{FunSuite, MustMatchers}

class GeometryTest extends FunSuite with MustMatchers {

  test("no images") {
    val t = Geometry.tiles(Size(200, 150), 2)(_)
    val images = Seq()
    val thrown = the[IllegalArgumentException] thrownBy t(images)
    thrown.getMessage.contains("one image") mustBe true
  }

  test("one row") {
    val t = Geometry.tiles(Size(200, 150), 2)(_)
    val images = Seq("a", "s")
    val ti = t(images)
    ti.width mustBe 400
    ti.height mustBe 150
    val r: Seq[Tile] = ti.tiles
    r.size mustBe 2
    r must contain inOrder(
      Tile("a", 0, 0),
      Tile("s", 200, 0),
    )
  }

  test("two rows A") {
    val t = Geometry.tiles(Size(200, 150), 2)(_)
    val images = Seq("a", "s", "r")
    val ti = t(images)
    ti.width mustBe 400
    ti.height mustBe 300
    val r: Seq[Tile] = ti.tiles
    r.size mustBe 4
    r must contain inOrder(
      Tile("a", 0, 0),
      Tile("s", 200, 0),
      Tile("r", 0, 150),
      Tile("a", 200, 150),
    )
  }

  test("two rows B") {
    val t = Geometry.tiles(Size(200, 150), 2)(_)
    val images = Seq("a", "s", "r", "k")
    val ti = t(images)
    ti.width mustBe 400
    ti.height mustBe 300
    val r: Seq[Tile] = ti.tiles
    r.size mustBe 4
    r must contain inOrder(
      Tile("a", 0, 0),
      Tile("s", 200, 0),
      Tile("r", 0, 150),
      Tile("k", 200, 150),
    )
  }

}
