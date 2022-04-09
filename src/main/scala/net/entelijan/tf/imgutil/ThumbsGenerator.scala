package net.entelijan.tf.imgutil

import java.nio.file.{Files, Path}
import scala.sys.process._


object ThumbsGenerator {

  private val size = 400
  private val qual = 50

  def createAllThumbnails(imgDir: Path): Unit = {

    if (Files.notExists(imgDir)) throw new IllegalStateException(s"Image directory ${imgDir.toAbsolutePath} does not exist")

    def createThumbnails(dir: Path): Unit = {

      def createThumbnail(img: Path, outDir: Path): Unit = {
        if (Files.isRegularFile(img)) {
          val nam = img.getFileName
          val tnPath = outDir.resolve(nam)
          if (Files.notExists(tnPath)) {
            val cmd = s"convert ${img.toAbsolutePath}  -thumbnail ${size}x$size^ -quality $qual% -gravity center -extent ${size}x$size ${tnPath.toAbsolutePath}"
            println(s"creating thumbnail running imagemagick: '$cmd'")
            cmd.!!
          }
        }
      }

      println(s"Creating thumbnails for $dir")
      val tnDir = dir.resolve("tn")
      if (Files.notExists(tnDir)) Files.createDirectory(tnDir)
      if (Files.isDirectory(dir)) Files.list(dir).forEach(img => createThumbnail(img, tnDir))
    }


    println(s"Image directory ${imgDir.toAbsolutePath}")

    Files.list(imgDir).forEach(dir => createThumbnails(dir))

  }


}
