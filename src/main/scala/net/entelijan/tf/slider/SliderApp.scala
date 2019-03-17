package net.entelijan.tf.slider

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}

import net.entelijan.tf.{ResCopy, TfUtil}
import TfUtil._
import scala.collection.JavaConverters._

object SliderApp extends App {

  val inDir = Paths.get("src/main/web/images/producer")

  val _fileNames = fileNames(inDir)

  val outName = inDir.getFileName.toString
  val outDir = Paths.get("target/slider")
  if (!Files.exists(outDir)) {
    Files.createDirectories(outDir)
  }
  val imagesDir = outDir.resolve(Paths.get("images"))
  if (!Files.exists(imagesDir)) {
    Files.createDirectories(imagesDir)
  }
  ResCopy.copyDir(inDir, imagesDir)
  ResCopy.copyDir(Paths.get("proto/WebContent/proto04/slick"), outDir)
  val outFile = outDir.resolve(s"$outName.html")
  tryWithRes(Files.newBufferedWriter(outFile)) {
    bw =>
      TfUtil.tryWithRes(new PrintWriter(bw)) {
        pw => pw.print(SliderTemplate.index(outName, _fileNames))
      }
  }
  println(s"wrote to $outFile")


  def fileNames(dir: Path): Seq[String] = {
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
