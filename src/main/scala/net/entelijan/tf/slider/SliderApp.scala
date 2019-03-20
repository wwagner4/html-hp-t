package net.entelijan.tf.slider

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import net.entelijan.tf.{ResCopy, TfUtil}
import TfUtil._
import scala.collection.JavaConverters._

object SliderApp extends App {

  val inDir = Paths.get("proto/WebContent/proto04/images/index")
  //val inDir = Paths.get("/home/wwagner4/.doc/a/r")

  create(inDir, "slick", SliderTemplate.slick)
  create(inDir, "owlcarousel", SliderTemplate.owl)

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
    ResCopy.copyDir(Paths.get(s"proto/WebContent/proto04/$name"), outDir)
    val outFile = outDir.resolve(s"$outName.html")
    tryWithRes(Files.newBufferedWriter(outFile)) {
      bw =>
        TfUtil.tryWithRes(new PrintWriter(bw)) {
          pw => pw.print(f(outName, _fileNames))
        }
    }
    println(s"wrote $name to $outFile")
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

