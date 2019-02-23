package net.entelijan.tf.imgoverview

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import scala.collection.JavaConverters._
import scala.util.control.NonFatal

case class Img(path: String, nr: Int, imgName: String)

object ImgOverview extends App {

  Seq("index", "jobs", "selfmade", "service").foreach(n => page(n))

  def page(name: String): Unit = {
    val outPath = Paths.get("target/gen", s"Bilder_$name.html")
    val inPath = Paths.get(s"src/main/web/images/$name")


    def toImg(index: Int, path: Path): Img = {
      val fname = path.getFileName.toString
      val spath = s"images/$name/$fname"
      Img(spath, index + 1, fname)
    }

    def createRows(inPath: Path): String = {
      Files.list(inPath).iterator()
        .asScala
        .toSeq
        .filter(p => !p.getFileName.toString.startsWith("."))
        .sortBy(p => p.getFileName.toString)
        .zipWithIndex
        .map { case (path, idx) => toImg(idx, path) }
        .grouped(3)
        .map(imgs => row(imgs))
        .mkString("\n")
    }

    withResources(new PrintWriter(Files.newBufferedWriter(outPath))) {
      pw =>
        val rows = createRows(inPath)
        pw.print(
          s"""
             |<html>
             |<head>
             |<link href='taschenfahrrad.css'	rel='stylesheet' type='text/css'>
             |</head>
             |<body>
             |<p class="imov_title">$name</p>
             |<table class="imov_table">
             |$rows
             |</table>
             |</body>
             |</html>
        """.stripMargin)
    }

    def row(imgs: Seq[Img]): String = {
      val tds = imgs.map(img => createTd(img)).mkString("\n")
      s"""
         |<tr class="imov_tr">
         |$tds
         |</tr>
      """.stripMargin
    }


    def createTd(img: Img): String = {
      s"""
         |<td><img width="300" height="225" src="${img.path}">
         |<p class="imov_txt">${img.nr} -- ${img.imgName}</p><td>
       """.stripMargin
    }

    def withResources[T <: AutoCloseable, V](r: => T)(f: T => V): V = {
      val resource: T = r
      require(resource != null, "resource is null")
      var exception: Throwable = null
      try {
        f(resource)
      } catch {
        case NonFatal(e) =>
          exception = e
          throw e
      } finally {
        closeAndAddSuppressed(exception, resource)
      }
    }

    def closeAndAddSuppressed(e: Throwable,
                              resource: AutoCloseable): Unit = {
      if (e != null) {
        try {
          resource.close()
        } catch {
          case NonFatal(suppressed) =>
            e.addSuppressed(suppressed)
        }
      } else {
        resource.close()
      }
    }

  }


}
