package net.entelijan.tf.imgutil

import os.*

def finalizeMain(): Unit = {

  def findFile(inDir: os.Path, pageId: String, nr: Int): os.Path = {
    val found = for file <- os.list(inDir / pageId) yield
      val split = file.baseName.split("-")
      val currentNr = split(2).toInt
      if currentNr == nr then Some(file) else None
    val result = found.filter(_.isDefined)
    assert(
      result.size >= 1,
      s"Found more than one result for $pageId and $nr in $inDir"
    )
    assert(result.size > 0, s"Found no result for $pageId and $nr in $inDir")
    return (result.toSeq)(0).get
  }

  println("Finalize...")
  val inDir = os.home / "tmp" / "tf" / "out"
  assert(os.exists(inDir), s"Input directory $inDir must exist")

  val outDir = os.home / "tmp" / "tf" / "finalout"

  val pages = List(
    (
      "index",
      Seq(32, 31, 60, 62, 28, 4, 6, 9, 12, 14, 13, 10, 16, 17, 26, 27, 36, 37,
        38, 40, 44, 63, 61, 44, 45, 46, 56, 59, 43, 61)
    ),
    (
      "selfmade",
      Seq(21, 0, 3, 5, 2, 7, 8, 11, 15, 18, 19, 25, 23, 30, 29, 35, 34, 43, 55,
        64, 68, 67, 78, 79, 75, 80, 81, 82, 91, 98, 97, 96)
    ),
    (
      "service",
      Seq(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 19, 20, 18,
        22, 23, 28, 33, 34, 44, 47, 48, 57, 51, 62, 63, 66, 67, 79, 75, 74, 72,
        71, 82, 81, 86)
    ),
    (
      "tour",
      Seq(60, 62, 63, 65, 50, 32, 30, 21, 13, 11, 2, 5, 14, 17, 18, 27, 34, 35,
        44, 45, 54, 55, 63, 76, 74, 84, 59, 48, 46, 43, 64)
    )
  )
  for (pageId, nrs) <- pages do
    println(s"$pageId $nrs")
    val currentOutDir = outDir / pageId
    if !os.exists(currentOutDir) then os.makeDir.all(currentOutDir)
    for (nr, i) <- nrs.zipWithIndex do
      val inFile = findFile(inDir, pageId, nr)
      assert(
        os.exists(inFile),
        s"File $inFile must exist based on pageId:$pageId and nr:$nr"
      )
      // println(s"## $pageId $nr ${inFile.last}")
      val newFileName = f"tf-3-$i%03d.${inFile.ext}"
      val newPath = currentOutDir / newFileName
      println(f"compy $inFile%50s -> $newPath")
      os.copy(inFile, newPath)
}
