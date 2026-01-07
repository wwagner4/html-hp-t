package net.entelijan.tf.imgutil

import net.entelijan.tf.Data
import os.*
import java.nio.file as nio

object ImgPrepare {
  def main(): Unit = {
    println(s"--> Prepare images")

    for page <- Data.pages do
      println(page.id)
      val homeDir = os.home
      val newDir = homeDir / "tmp" / "tf" / "new" / page.id
      if !os.exists(newDir) then
        throw IllegalStateException(f"New dir $newDir does not exist")
      val oldDir =
        homeDir / "prj" / "html-hp-t" / "src" / "main" / "web" / "common" / "images" / page.id
      if !os.exists(oldDir) then
        throw IllegalStateException(f"Old dir $oldDir does not exist")
      println(s"-- in dirs exist $oldDir $newDir")

      val outDir = homeDir / "tmp" / "tf" / "out" / page.id
      if !os.exists(outDir) then
        os.makeDir.all(outDir)
        println(s"-- Created out dir $outDir")
      else
        val cmd = ("/usr/bin/bash", "-c", s"rm -rf $outDir/*")
        val result = os.call(cmd = cmd, cwd = outDir)
        println(
          s"-- Cleard contents of out dir $outDir exit:${result.exitCode}"
        )

      copyRename(newDir, "tf-0", outDir)
      copyRename(oldDir, "tf-1", outDir)
      {
        val cmd = ("/usr/bin/bash", "-c", s"chmod -R 666 *")
        val result = os.call(cmd = cmd, cwd = outDir)
        println(s"-- Chmod 666 on $outDir exit:${result.exitCode}")
      }
      for image <- os.list(outDir) do
        ImageTransform.shrinkImage(nio.Path.of(s"$image"))
      println(s"-- shrinked images in $outDir")

    println("<-- Prepare images")
  }

  def copyRename(inDir: Path, prefix: String, outDir: Path): Unit = {
    println(s"-- Copy with prefix $prefix $inDir -> $outDir")
    for (file, i) <- os.list(inDir).zipWithIndex if os.isFile(file) do
      val newFileName = f"$prefix-$i%03d.${file.ext}"
      val newFilePath = outDir / newFileName
      os.copy(file, newFilePath)
      println(f"-- copied ${file} ${newFilePath}")
  }
}
