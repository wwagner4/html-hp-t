package net.entelijan.tf.imgutil

import net.entelijan.tf.{Data, ResCopy}
import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import scala.jdk.CollectionConverters._
import scala.util.control.NonFatal
import os.call

case class TfFileName(
    origin: Int,
    nr: Int
)

def parseTfFilename(name: String): TfFileName = {

  val filenamePattern = """.+-(\d+)-(\d+)\.(jpg|JPG|jpeg|JPEG|png|PNG)""".r

  name match {
    case filenamePattern(origin, nr, _) => TfFileName(origin.toInt, nr.toInt)
    case _                              =>
      throw IllegalArgumentException(
        s"Filename '$name' does not fulfill naming convention. E.g 'tf-0-002.jpg' would be valid"
      )
  }

}

enum Origin:
  case New
  case Old

case class Img(path: String, origin: Origin, nr: Int, imgName: String)

case class Config(
    // For base path from within the project use Paths.get(s"src/main/web/common/images"
    val baseInPath: Path =
      Path.of(System.getProperty("user.home"), "tmp", "tf", "out"),
    val outDir: Path = Paths.get("target", "overview"),
    val ncol: Int = 10,
    val prefix: String = "taschenfahrrad_2026",
    val shrink: ShrinkConfig =
      ShrinkConfig(size = 300, threshold = 50_000, qualityPerc = 80)
)

object ImgOverview {

  def main(): Unit = {
    println("--> Oveview")
    val config = Config()

    if (Files.notExists(config.outDir)) Files.createDirectory(config.outDir)
    os.call(cmd = ("sh", "-c", s"rm -rf ${config.outDir}/*"))

    Data.pages.foreach(n => page(n.id, config))

    println()
    println(s"--- In folder: ${config.baseInPath}")
    println(s"--- Out folder: ${config.outDir.toAbsolutePath()}")
    println("<-- Oveview")
  }

  def betterName(name: String): String = {
    return if name == "selfmade" then "surly"
    else if name == "index" then "start"
    else name
  }

  def page(name: String, config: Config): Unit = {

    val inPath = config.baseInPath.resolve(name)
    if Files.notExists(inPath) then
      throw IllegalStateException(s"$inPath does not exist")

    val outPath =
      config.outDir.resolve(s"${config.prefix}_${betterName(name)}.html")
    ResCopy.copyDir(inPath, config.outDir)

    val pageOutDir = config.outDir.resolve(name)
    shrinkAll(os.Path(pageOutDir.toAbsolutePath()), config.shrink)

    def toImg(index: Int, path: Path): Img = {
      val fname = path.getFileName.toString
      val fn = parseTfFilename(fname)
      val origin = fn.origin match {
        case 0   => Origin.New
        case 1   => Origin.Old
        case any =>
          throw IllegalStateException(
            s"Unknown origin '$any'. Must be '0' or '1"
          )
      }
      val nr = fn.nr
      val spath = s"$path"
      Img(
        path = s"$name/$fname",
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
        .grouped(config.ncol)
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
             |<p class="imov_title">${betterName(name)} ${config.prefix}</p>
             |<table class="imov_table">
             |$rows
             |</table>
             |</body>
             |</html>
        """.stripMargin)

      println(s"Wrote overview to $outPath")

      val htmlPath = outPath.getFileName().toString()
      val pdfPath = s"${config.prefix}_${betterName(name)}.pdf"
      println(s"Writing pdf to $pdfPath from $htmlPath")
      val cmd = List(
        "wkhtmltopdf",
        "-q",
        "--enable-local-file-access",
        htmlPath,
        pdfPath
      )
      val outFolder = config.outDir.toAbsolutePath().toString()
      println(s"Creating pdf from '${cmd.mkString(" ")}' in $outFolder")
      os.call(cmd = cmd, cwd = os.Path(outFolder))

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
