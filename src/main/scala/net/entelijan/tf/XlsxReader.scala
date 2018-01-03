package net.entelijan.tf

import java.io.File
import java.io.FileInputStream

import scala.collection.JavaConverters._

import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.XSSFWorkbook

case class XlsxReader(file: File) {

  def rows: List[List[String]] = {

    println(s"reading products from $file")

    def cellToString(cell: Cell): String = {
      cell.getCellTypeEnum match {
        case CellType.STRING => cell.getStringCellValue
        case CellType.BLANK => ""
        case _ => throw new IllegalStateException("Unknown type " + cell.getCellTypeEnum)
      }
    }

    def rowToStringList(row: Row): List[String] = {
      row.cellIterator().asScala.toList.map { cellToString }
    }

    val excelFile = new FileInputStream(file)
    val workbook = new XSSFWorkbook(excelFile)
    val datatypeSheet = workbook.getSheetAt(0)
    val rowList = datatypeSheet.iterator().asScala.toList

    rowList.map { rowToStringList }
  }

}

