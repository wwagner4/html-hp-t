package net.entelijan.tf.imgutil

import net.entelijan.tf.Data
import os.*
import java.nio.file as nio

object ImgPrepare {
  def main(): Unit = {
    println(s"--> Prepare images")

    val homeDir = os.home

    val newBaseDir = homeDir / "tmp" / "tf" / "new"
    assert(os.exists(newBaseDir), s"New base dir must exist. $newBaseDir")

    val oldBaseDir =
      homeDir / "prj" / "html-hp-t" / "src" / "main" / "web" / "common" / "images"
    assert(os.exists(oldBaseDir), s"Old base dir must exist. $oldBaseDir")

    val outBaseDir = homeDir / "tmp" / "tf" / "out"

    for page <- Data.pages do
      println(page.id)
      val newDir = homeDir / "tmp" / "tf" / "new" / page.id
      if !os.exists(newDir) then
        throw IllegalStateException(f"New dir $newDir does not exist")
      val oldDir = oldBaseDir / page.id
      if !os.exists(oldDir) then
        throw IllegalStateException(f"Old dir $oldDir does not exist")
      println(s"-- in dirs exist $oldDir $newDir")

      val outDir = outBaseDir / page.id
      if !os.exists(outDir) then
        os.makeDir.all(outDir)
        println(s"-- Created out dir $outDir")
      else
        val cmd = ("/usr/bin/bash", "-c", s"rm -rf $outDir/*")
        val result = os.call(cmd = cmd, cwd = outDir)
        println(
          s"-- Cleard contents of out dir $outDir exit:${result.exitCode}"
        )

      val currentIndex = copyRename(newDir, 0, "tf-0", outDir)
      copyRename(oldDir, currentIndex, "tf-1", outDir)
      {
        val cmd = ("/usr/bin/bash", "-c", s"chmod -R 666 *")
        val result = os.call(cmd = cmd, cwd = outDir)
        println(s"-- Chmod 666 on $outDir exit:${result.exitCode}")
      }
      for image <- os.list(outDir) do
        ImageTransform.shrinkImage(nio.Path.of(s"$image"))
      println(s"-- shrinked images in $outDir")

    println(s" -- Prepared images from:")
    println(s" -- new: $newBaseDir")
    println(s" -- old: $oldBaseDir")
    println(s"<-- to: $outBaseDir")
  }

  def copyRename(
      inDir: Path,
      startIndex: Int,
      prefix: String,
      outDir: Path
  ): Int = {
    println(s"-- Copy with prefix $startIndex $prefix $inDir -> $outDir")
    var i = startIndex
    for file <- os.list(inDir) if os.isFile(file) do
      val newFileName = f"$prefix-$i%03d.${file.ext}"
      val newFilePath = outDir / newFileName
      os.copy(file, newFilePath)
      println(f"-- copied ${file} ${newFilePath}")
      i += 1
    i
  }
}
