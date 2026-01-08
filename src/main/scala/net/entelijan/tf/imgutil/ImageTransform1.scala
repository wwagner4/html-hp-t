package net.entelijan.tf.imgutil

import os.*

case class ShrinkConfig(
    size: Int = 1200,
    threshold: Int = 1_000_000,
    qualityPerc: Int = 80
)

def shrinkAll(imgDir: os.Path, config: ShrinkConfig): Unit = {
  println(s"Shrink all in $imgDir")
  for file <- os.list(imgDir) do shrink(file, config)
}

def createAllThumbnails(imgDir: os.Path): Unit = {
  val recreate = false

  val tnDir = imgDir / "tn"
  if !os.exists(tnDir) then os.makeDir(tnDir)
  if recreate then clearDirectory(tnDir)
  val recreateInfo = if recreate then "RECREATE " else ""
  println(s"Creating thumbnails ${recreateInfo}for $imgDir")
  for file <- os.list(imgDir) do createThumbnail(file, tnDir)
}

private def shrink(file: Path, config: ShrinkConfig): Unit = {
  if !os.isFile(file) then println(s"shrink. Not a file $file")
  else if os.size(file) < config.threshold then
    println(s"## WARNING not shrinking $file")
  else
    val cmd = (
      "mogrify",
      "-auto-orient",
      "-resize",
      s"${config.size}>",
      "-quality",
      s"${config.qualityPerc}%",
      file
    )
    val result = os.call(cmd = cmd, check = false)
    if result.exitCode != 0 then
      println(s"## ERROR ${result.exitCode} shrinking $file")
}

private def clearDirectory(dir: Path): Unit = {
  if !os.isDir(dir) then
    throw IllegalStateException(s"$dir illegal. Only directories allowed")
  for file <- os.list(dir) do
    if os.isDir(file) then clearDirectory(file)
    else os.remove(file)
}

private def createThumbnail(file: os.Path, outDir: Path): Unit = {
  val size = 400
  val quality = 70
  if !os.isFile(file) then println(s"Create Thumbnail: Not a file $file")
  else
    val nam = s"${file.baseName}.${file.ext}"
    val tnPath = outDir / nam
    if !os.exists(tnPath) then
      val cmd = (
        "convert",
        file,
        "-auto-orient",
        "-thumbnail",
        s"${size}x${size}^",
        "-quality",
        s"$quality%",
        "-gravity",
        "center",
        "-extent",
        s"${size}x$size",
        tnPath
      )
      val result = os.call(cmd = cmd, check = false)
      if result.exitCode != 0 then
        println(s"## ERROR ${result.exitCode} creating thumbnail for $file")
}
