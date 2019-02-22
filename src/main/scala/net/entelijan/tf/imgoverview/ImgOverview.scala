package net.entelijan.tf.imgoverview

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import scala.collection.JavaConverters._
import scala.util.control.NonFatal

object ImgOverview extends App {

  Seq("index", "jobs", "selfmade", "service").foreach(n => page(n))

  def page(name: String): Unit = {
    val outPath = Paths.get("target/gen", s"Bilder_$name.html")
    val inPath = Paths.get(s"src/main/web/images/$name")


    def createTableRow(index: Int, path: Path): String = {
      val fname = path.getFileName.toString
      val spath = s"images/$name/$fname"
      return row(spath, index, fname)
    }

    def createRows(inPath: Path): String = {
      Files.list(inPath).iterator()
        .asScala
        .toSeq
        .zipWithIndex
        .map { case (path, idx) => createTableRow(idx, path) }
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
             |<h1>$name</h1>
             |<table>
             |$rows
             |</table>
             |</body>
             |</html>
        """.stripMargin)
    }

    def row(path: String, nr: Int, imgName: String): String = {
      s"""
         |<tr>
         |<td><img width="300" height="190" src="$path"><td>
         |<td>$nr<td>
         |<td>$imgName<td>
         |</tr>
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