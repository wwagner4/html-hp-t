package net.entelijan.tf

import java.io.File

import scala.util.{Failure, Success, Try}

object TfUtil {

  def inTargetDir(name: String): File = getCreateDir(Option(targetDir), name)

  def genDir: File = getCreateDir(Option(targetDir), "gen")

  def genReportDir: File = getCreateDir(Option(workDir), "gen-report")

  private def workDir: File = getCreateDir(Option(workDirGeneral), "work-html-hp-t")

  private def targetDir: File = getCreateDir(None, "target")

  private def homeDir: File = getCreateDir(None, System.getProperty("user.home"))

  private def workDirGeneral: File = getCreateDir(Option(homeDir), "work")

  private def getCreateDir(parent: Option[File], name: String): File = {
    val dir = parent.fold(new File(name))(p => new File(p, name))
    if (dir.exists()) {
      require(dir.isDirectory)
      dir
    } else {
      dir.mkdirs()
      dir
    }
  }

  def tryWithRes[A <: AutoCloseable, B](resource: A)(block: A => B): B = {
    Try(block(resource)) match {
      case Success(result) =>
        resource.close()
        result
      case Failure(e) =>
        resource.close()
        throw e
    }
  }
}
