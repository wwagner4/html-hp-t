package net.entelijan.tf.imgutil

import java.nio.file.{Files, Path}
import scala.sys.process._

object ImageTransform {

  private val size = 400
  private val shrinkSize = 1200
  private val quality = 70

  def createAllThumbnails(imgDir: Path): Unit = {
    createAll(imgDir, createThumbnail)
  }

  def shrinkAll(imgDir: Path): Unit = {
    println(s"## Shrink all ${imgDir}")
    createAll(imgDir, shrinkImage)
  }

  private def createThumbnail(img: Path): Unit = {
    val outDir = img.getParent.resolve("tn")
    if (Files.notExists(outDir)) Files.createDirectories(outDir)
    if (Files.isRegularFile(img)) {
      val nam = img.getFileName
      val tnPath = outDir.resolve(nam)
      if (Files.notExists(tnPath)) {
        val cmd =
          s"convert -auto-orient ${img.toAbsolutePath} -thumbnail ${size}x$size^ -quality $quality% -gravity center -extent ${size}x$size ${tnPath.toAbsolutePath}"
        println(s"creating thumbnail running imagemagick: '$cmd'")
        cmd.!!
      }
    }
  }

  def shrinkImage(img: Path): Unit = {
    if (Files.isRegularFile(img) && Files.size(img) > 1.0e6) {
      println(s"Shrinking image $img size: ${Files.size(img)}")
      val cmd =
        s"mogrify -auto-orient -resize $shrinkSize> -quality 80% ${img.toAbsolutePath}"
      println(s"shrinking image running imagemagick: '$cmd'")
      cmd.!!
    } else {
      println(s"Not shrinking image: '$img'")
    }
  }

  private def createAll(imgDir: Path, f: Path => Unit): Unit = {

    if (Files.notExists(imgDir))
      throw new IllegalStateException(
        s"Image directory ${imgDir.toAbsolutePath} does not exist"
      )

    def createThumbnails(dir: Path): Unit = {

      if (Files.isDirectory(dir)) {
        println(s"Creating thumbnails for $dir")
        val tnDir = dir.resolve("tn")
        if (Files.notExists(tnDir)) Files.createDirectory(tnDir)
        if (Files.isDirectory(dir)) Files.list(dir).forEach(img => f(img))
      }
    }
    println(s"Image directory ${imgDir.toAbsolutePath}")
    Files.list(imgDir).forEach(dir => createThumbnails(dir))
  }
}
