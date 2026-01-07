package net.entelijan.tf.imgutil

import net.entelijan.tf.{Data, ResCopy}
import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal

enum Origin:
  case New
  case Old

case class Img(path: String, origin: Origin, nr: Int, imgName: String)

object ImgOverview {

  def main(): Unit = {
    Data.pages.foreach(n => page(n.id))
  }

  def betterName(name: String): String = {
    return if name == "selfmade" then "surly"
    else name
  }

  def page(name: String): Unit = {
    val outDir =
      Path.of(System.getProperty("user.home"), "tmp", "tf", "overview")
    val ncol = 10

    val inPath = Paths.get(s"src/main/web/common/images/$name")
    if Files.notExists(inPath) then
      throw IllegalStateException(s"$inPath does not exist")
    if (Files.notExists(outDir)) Files.createDirectory(outDir)
    val outPath = outDir.resolve(s"Bilder_${betterName(name)}.html")
    ResCopy.copyDir(inPath, outDir)

    def toImg(index: Int, path: Path): Img = {
      val fname = path.getFileName.toString
      val splited = fname.split("-")
      val origin = splited(1) match {
        case "0" => Origin.New
        case "1" => Origin.Old
        case any =>
          throw IllegalStateException(
            s"Unknown origin '$any'. Must be '0' or '1"
          )
      }
      val nr = splited(2).split("\\.")(0).toInt

      val spath = s"$name/$fname"
      Img(
        path = spath,
        nr = nr,
        imgName = fname,
        origin = origin
      )
    }

    def createRows(inPath: Path): String = {
      Files
        .list(inPath)
        .iterator()
        .asScala
        .toSeq
        .filter(p => !p.getFileName.toString.startsWith("."))
        .sortBy(p => p.getFileName.toString)
        .zipWithIndex
        .map { case (path, idx) => toImg(idx, path) }
        .grouped(ncol)
        .map(imgs => row(imgs))
        .mkString("\n")
    }

    withResources(new PrintWriter(Files.newBufferedWriter(outPath))) { pw =>
      val css = """
      |body {font-family: sans; }
      |tr { margin-top: 3em; }
      |.imov_txt { vertical-align: bottom; padding-top: 3em}
      |.imov_title { font-size: 2em; }
      """

      val rows = createRows(inPath)
      pw.print(s"""
             |<html>
             |<head>
             |<style>
             |$css
             |</style>
             |</head>
             |<body>
             |<p class="imov_title">${betterName(name)}</p>
             |<table class="imov_table">
             |$rows
             |</table>
             |</body>
             |</html>
        """.stripMargin)

      println(s"Wrote overview to $outPath")
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
      val marker = if img.nr < 16 then "#" else ""
      s"""
         |<td class="imov_txt">
         |<img class="imov_img" src="${img.path}" width="150"></br>
         |${img.origin} ${img.nr} $marker
         |</td>
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

    def closeAndAddSuppressed(e: Throwable, resource: AutoCloseable): Unit = {
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
