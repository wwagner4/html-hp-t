package net.entelijan.tf

import org.scalatest.FunSuite

class TestCaseModelReader extends FunSuite {

  test("Empty") {
    val map = ModelReader.readModels(List.empty)
    assert(map.isEmpty)
  }

  test("No producer defined") {
    val in = List(
      List("", "", "x"))
    try {
      ModelReader.readModels(in)
      fail("Exception not thrown")
    } catch {
      case e: IllegalStateException =>
        assert("No producer defined in line 1" === e.getMessage)
      case e: Throwable =>
        throw e
    }
  }

}