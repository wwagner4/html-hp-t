package net.entelijan.tf.slider

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import net.entelijan.tf.{ResCopy, TfUtil}
import TfUtil._
import net.entelijan.tf.tiles.TilesFromDirectory

import scala.collection.JavaConverters._

object SliderApp extends App {

  val baseInDir = Paths.get("proto/WebContent/proto04")

  //val imagesInDir = Paths.get("proto/WebContent/proto04/images/index")
  val imagesInDir = Paths.get("/home/wwagner4/.doc/a/r")

  //create(baseInDir, imagesInDir, "slick", SliderTemplate.slick)
  //create(baseInDir, imagesInDir, "owlcarousel", SliderTemplate.owl)
  create(baseInDir, imagesInDir, "glide", SliderTemplate.glide, 10)

  private def create(baseIndDir: Path, imagesInDir: Path, sliderName: String, f: (String, Seq[String]) => String, cols: Int = 4): Unit = {
    val _fileNames = imageFileNames(imagesInDir)

    val outDir = Paths.get(s"target/$sliderName")
    if (!Files.exists(outDir)) {
      Files.createDirectories(outDir)
    }
    val imagesDir = outDir.resolve(Paths.get("images"))
    if (!Files.exists(imagesDir)) {
      Files.createDirectories(imagesDir)
    }
    ResCopy.copyDir(imagesInDir, imagesDir)
    ResCopy.copyDir(baseInDir.resolve("css"), outDir)
    ResCopy.copyDir(baseInDir.resolve("js"), outDir)

    val pageName = imagesInDir.getFileName.toString
    val fileNameStr = s"tiles$pageName"
    val tilesOutDir = imagesDir.resolve("tiles")

    val tilesFile = tilesOutDir.resolve(fileNameStr + ".jpg")
    if (!Files.exists(tilesFile)) {
      println(s"ceating tiles in $tilesFile")
      TilesFromDirectory.squaredTiles(fileNameStr, cols, 200, 5, imagesDir.resolve(pageName), tilesOutDir)
    }

    ResCopy.copyDir(Paths.get(s"proto/WebContent/proto04/$sliderName"), outDir)
    val htmlFile = outDir.resolve(s"$pageName.html")
    tryWithRes(Files.newBufferedWriter(htmlFile)) {
      bw =>
        TfUtil.tryWithRes(new PrintWriter(bw)) {
          pw => pw.print(f(pageName, _fileNames))
        }
    }
    println(s"wrote $sliderName to $htmlFile")
  }

  def cpDir(dir: Path, toDir: Path): Unit = {
    val dirName: Path = dir.getParent
    val outDir = toDir.resolve(dirName)
    if (!Files.exists(outDir)) {
      Files.createDirectories(outDir)
    }
    ResCopy.copyDir(dir, outDir)
  }


  def imageFileNames(dir: Path): Seq[String] = {
    Files.list(dir)
      .iterator
      .asScala
      .toSeq
      .filter(p => p.getFileName.toString.toLowerCase.endsWith("jpg")
        || p.toString.toLowerCase.endsWith("jpeg")
        || p.toString.toLowerCase.endsWith("png"))
      .map(p => p.getFileName.toString)
      .sorted
  }
}

