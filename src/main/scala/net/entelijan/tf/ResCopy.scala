package net.entelijan.tf

import java.io.File
import java.nio.file.{FileSystems, Files, Path}
import java.nio.file.attribute.BasicFileAttributes

object ResCopy {

  def copyDir(fromDir: Path, toParentDir: Path): Unit = {
    require(Files.exists(fromDir) && Files.isDirectory(fromDir), s"$fromDir must exist and must be a directory")
    if (!Files.exists(toParentDir)) {
      Files.createDirectories(toParentDir)
    }
    val name = fromDir.getFileName
    val toDir = toParentDir.resolve(name)
    if (!Files.exists(toDir)) {
      Files.createDirectories(toDir)
    }
    copy(fromDir.toFile, toDir.toFile)
  }

  def copy(from: File, to: File): Unit = {
    require(from.isDirectory, "%s is not a directory" format from)
    require(to.isDirectory, "%s is not a directory" format to)
    val toFiles = to.listFiles().toList
    for (fromFile <- from.listFiles()) {
      if (fromFile.isDirectory) {
        findFile(fromFile.getName, toFiles) match {
          case None =>
            val newDir = new File(to, fromFile.getName)
            newDir.mkdirs()
            copy(fromFile, newDir)
          case Some(toFile) => copy(fromFile, toFile)
        }
      } else {
        findFile(fromFile.getName, toFiles) match {
          case None => copyFile(fromFile, to)
          case Some(toFile) => if (leftIsYounger(fromFile, toFile)) copyFile(fromFile, to)
        }
      }
    }

  }

  def copyFile(f: File, dir: File): Unit = {
    import java.io.{File, FileInputStream, FileOutputStream}
    require(dir.isDirectory, "%s is not a directory" format dir)
    val newFile = new File(dir, f.getName)
    new FileOutputStream(newFile).getChannel.transferFrom(
      new FileInputStream(f).getChannel, 0, Long.MaxValue)
    println("copied %s to %s" format(f, dir))
  }

  def leftIsYounger(left: File, right: File): Boolean = {
    def time(f: File): Long = {
      val p: Path = FileSystems.getDefault.getPath(f.getAbsolutePath)
      val attr = Files.readAttributes(p, classOf[BasicFileAttributes])
      attr.lastModifiedTime().toMillis
    }

    time(left) > time(right)
  }

  @scala.annotation.tailrec
  def findFile(name: String, files: List[File]): Option[File] = {
    files match {
      case Nil => None
      case f :: rest => if (f.getName == name) Option(f)
      else findFile(name, rest)
    }
  }

}
