package net.entelijan.tf

import org.scalatest.FunSuite

class TryoutSuite extends FunSuite {
  
  test("grouping") {
    val l = (1 to 20).toList
    println(l)
    val l1 = l.grouped(5).toList
    println("li = %s" format l1)
  }  

}