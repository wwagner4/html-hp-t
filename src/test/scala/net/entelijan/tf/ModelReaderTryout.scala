package net.entelijan.tf

import java.io.File

object ModelReaderTryout extends App {

  val workDirName = "/Users/wwagner4/prj/taschenfahrrad/work"
  val fileName = "Products.xlsx"
  val workDir = new File(workDirName)
  val file = new File(workDir, fileName)
    val reader = XlsxReader(file)

  val rows = reader.rows
  
  val models = ModelReader.readModels(rows)
  
  models.foreach { case (id, ms) => 
    println(s"Producer $id")  
    
    println(ms.mkString("\n"))
    println()
  
  }

  

}