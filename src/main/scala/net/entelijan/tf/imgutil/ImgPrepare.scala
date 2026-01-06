package net.entelijan.tf.imgutil

import net.entelijan.tf.Data
import os.*

object ImgPrepare {
  def main(): Unit = {
    println("--> Prepare images")

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
      if !os.exists(outDir) then os.makeDir.all(outDir)
      println(s"-- out dir $outDir")

      copyRename(newDir, "tf-0", outDir)
      copyRename(oldDir, "tf-1", outDir)

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
