package net.entelijan.tf

import java.io.File

object XlsxTryout extends App {

  val workDirName = "/Users/wwagner4/prj/taschenfahrrad/work"
  val fileName = "Products.xlsx"
  val workDir = new File(workDirName)
  val file = new File(workDir, fileName)

  val reader = XlsxReader(file)

  reader.rows.foreach { row => println(row.mkString("|")) }

}