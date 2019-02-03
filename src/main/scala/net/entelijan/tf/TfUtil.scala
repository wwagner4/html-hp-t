package net.entelijan.tf

import java.io.File

object TfUtil {

  def genDir: File = getCreateDir(Some(targetDir), "gen")

  def genReportDir: File = getCreateDir(Some(workDir), "gen-report")

  private def workDir: File = getCreateDir(Some(workDirGeneral), "work-html-hp-t")

  private def targetDir: File = getCreateDir(None, "target")

  private def homeDir = getCreateDir(None, System.getProperty("user.home"))

  private def workDirGeneral: File = getCreateDir(Some(homeDir), "work")

  private def getCreateDir(parent: Option[File], name: String): File = {
    val dir = parent.map(p => new File(p, name)).getOrElse(new File(name))
    if (dir.exists()) {
      require(dir.isDirectory)
      dir
    } else {
      dir.mkdirs()
      dir
    }
  }

}
