package net.entelijan.tf

import java.io.PrintWriter
import java.io.File

case class ReportGen(producers: List[Producer]) {

  val sepa = ";"

  import ReportGenUtil._

  def genReport(pw: PrintWriter): Unit = {
    genHeader(pw)
    genProdList(pw)
  }

  private def genHeader(pw: PrintWriter): Unit = {
    val hStr = s"ID${sepa}NAME${sepa}ID${sepa}ACTIVE${sepa}NAME${sepa}EQUIPMENT${sepa}SIZE${sepa}PRIZE"
    pw.println(hStr)
  }

  private def genProdList(pw: PrintWriter): Unit = {
    for (p <- producers) {
      for ((m, i) <- p.models.zipWithIndex) {
        val formatStr = s"%s$sepa%s$sepa%s$sepa$sepa%s$sepa%s$sepa%s$sepa%s%n"
        if (i == 0) pw.printf(formatStr format (p.id, p.name, m.id, m.name, m.equipment.getOrElse(""), m.size.getOrElse(""), convPrize(m.prize.getOrElse("?"))))
        else pw.printf(formatStr format ("", "", m.id, m.name, m.equipment.getOrElse(""), m.size.getOrElse(""), convPrize(m.prize.getOrElse("?"))))
      }
      (1 to 10) foreach { _ => pw.println() }
    }
  }

  def genProducers(pw: PrintWriter): Unit = {
    for (p <- producers) {
      val x: List[String] = p.models.foldLeft(List.empty[String])((aggr, model) => imageFileNames(p, model) ::: aggr)
      val y = images(p)
      val unused = y diff x
      pw.printf("%s;%s%n", p.name, unused.mkString(","))
    }
  }

  def images(prod: Producer): List[String] = {
    T.imagesFileList(prod).map(_.getName)
  }

  def images(prod: Producer, m: Model): List[File] = {
    T.imagesFileList(prod).filter(_.getName.contains(m.id))
  }

  def imageFileNames(prod: Producer, m: Model): List[String] = {
    images(prod, m).map(_.getName())
  }

}

object ReportGenUtil {
  def convPrize(p: String): String = {
    val R = """<span.*>(.*)</span>(.*)""".r
    p match {
      case R(a, b) => "--%s--%s" format (a, b)
      case _ => p
    }
  }
}