package net.entelijan.tf.tiles

import com.sun.javafx.iio.common.ScalerFactory
import org.scalatest.{Assertion, FunSuite, MustMatchers}

class GeometryTest extends FunSuite with MustMatchers {

  test("different ratios") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq(
      Image("a", Size(200, 150)),
      Image("s", Size(200, 250)),
    )
    val thrown = the [IllegalArgumentException] thrownBy t(images)
    thrown.getMessage.contains("same ratio") mustBe true
  }

  test("no images") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq()
    val thrown = the [IllegalArgumentException] thrownBy t(images)
    thrown.getMessage.contains("one image") mustBe true
  }

  test("no scale") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq(
      Image("a", Size(200, 150)),
      Image("s", Size(200, 150)),
    )
    val r: Seq[TileResult] = t(images)
    r must contain inOrder (
      TileResult("a", ScaleFactors(1, 1), 0, 0),
      TileResult("s", ScaleFactors(1, 1), 200, 0),
    )
  }

  test("one scale") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq(
      Image("a", Size(200, 150)),
      Image("s", Size(400, 300)),
    )
    val r: Seq[TileResult] = t(images)
    r must contain inOrder (
      TileResult("a", ScaleFactors(1, 1), 0, 0),
      TileResult("s", ScaleFactors(0.5, 0.5), 200, 0),
    )
  }

  test("no scale two rows") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq(
      Image("a", Size(200, 150)),
      Image("s", Size(200, 150)),
      Image("d", Size(200, 150)),
      Image("f", Size(200, 150)),
    )
    val r: Seq[TileResult] = t(images)
    r must contain inOrder (
      TileResult("a", ScaleFactors(1, 1), 0, 0),
      TileResult("s", ScaleFactors(1, 1), 200, 0),
      TileResult("d", ScaleFactors(1, 1), 0, 150),
      TileResult("f", ScaleFactors(1, 1), 200, 150),
    )
  }

  test("no scale two rows from tree images") {
    val t = Geometry.tiles(400, 2)(_)
    val images = Seq(
      Image("a", Size(200, 150)),
      Image("s", Size(200, 150)),
      Image("d", Size(200, 150)),
    )
    val r: Seq[TileResult] = t(images)
    r must contain inOrder (
      TileResult("a", ScaleFactors(1, 1), 0, 0),
      TileResult("s", ScaleFactors(1, 1), 200, 0),
      TileResult("d", ScaleFactors(1, 1), 0, 150),
      TileResult("a", ScaleFactors(1, 1), 200, 150),
    )
  }

}
