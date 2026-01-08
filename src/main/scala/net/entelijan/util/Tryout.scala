package net.entelijan.tf.util

import os.*
import net.entelijan.tf.imgutil as iu

def tryoutMain(): Unit = {
  println("tryout.....")
  val inDir = os.home / "tmp" / "tf" / "tour"
  iu.shrinkAll(inDir, iu.ShrinkConfig())
}
