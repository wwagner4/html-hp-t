package net.entelijan.tf

import org.scalatest.FunSuite

class TestCaseGenerator extends FunSuite {
  
  test("prize cleanup") {
    val t = """<span style="text-decoration:line-through;">599€</span> 499€"""
    val c = ReportGenUtil.convPrize(t)
    assert(c === """--599€-- 499€""")
    
  }
  
}