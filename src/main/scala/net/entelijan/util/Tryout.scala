package net.entelijan.tf.util

import os.*
import net.entelijan.tf.imgutil as iu

def tryoutMain(): Unit = {
  println("tryout.....")

  val testDir = os.home / "tmp" / "tf" / "out" / "index"
  for file <- os.list(testDir).take(10) do
    println(file.last)
    println(file.baseName)

}
