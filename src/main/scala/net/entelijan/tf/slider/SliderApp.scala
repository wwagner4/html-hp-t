package net.entelijan.tf.slider

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import net.entelijan.tf.{ResCopy, TfUtil}
import TfUtil._
import net.entelijan.tf.tiles.TilesFromDirectory

import scala.collection.JavaConverters._

object SliderApp extends App {

  val inDir = Paths.get("proto/WebContent/proto04/images/index")
  //val inDir = Paths.get("/home/wwagner4/.doc/a/r")

  //create(inDir, "slick", SliderTemplate.slick)
  //create(inDir, "owlcarousel", SliderTemplate.owl)
  create(inDir, "glide", SliderTemplate.glide)

  private def create(inDir: Path, name: String, f: (String, Seq[String]) => String): Unit = {
    val _fileNames = imageFileNames(inDir)

    val outName = inDir.getFileName.toString
    val outDir = Paths.get(s"target/$name")
    if (!Files.exists(outDir)) {
      Files.createDirectories(outDir)
    }
    val imagesDir = outDir.resolve(Paths.get("images"))
    if (!Files.exists(imagesDir)) {
      Files.createDirectories(imagesDir)
    }
    ResCopy.copyDir(inDir, imagesDir)

    TilesFromDirectory.squaredTiles(s"tiles$outName", 4, 200, 5, imagesDir.resolve(outName), imagesDir.resolve("tiles"))

    ResCopy.copyDir(Paths.get(s"proto/WebContent/proto04/$name"), outDir)
    val htmlFile = outDir.resolve(s"$outName.html")
    tryWithRes(Files.newBufferedWriter(htmlFile)) {
      bw =>
        TfUtil.tryWithRes(new PrintWriter(bw)) {
          pw => pw.print(f(outName, _fileNames))
        }
    }
    println(s"wrote $name to $htmlFile")
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
  }
}

