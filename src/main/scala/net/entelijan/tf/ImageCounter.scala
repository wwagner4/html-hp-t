package net.entelijan.tf

import java.io.File

object ImageCounter {
  
  val imageDirName = "src/main/web/images"
  val imageDir = new File(imageDirName)
  
  def count(prodId: String, modelId: String): Int = {
    val prodDir = new File(imageDir, prodId)
    prodDir.list().filter(_.startsWith(modelId)).length
  }
  
}