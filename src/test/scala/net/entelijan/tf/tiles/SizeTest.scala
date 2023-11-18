package net.entelijan.tf.tiles

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers._

class SizeTest extends AnyFunSuite {

  test("ratio equal") {
    Size(200, 400).ratio mustBe Size(4000, 8000).ratio
    Size(200, 400).ratio mustBe Size(4000, 8001).ratio
    Size(200, 400).ratio mustBe Size(4000, 8010).ratio
    Size(200, 400).ratio mustBe Size(40, 80).ratio
  }

}
