package net.entelijan.tf

import java.io.{File, PrintWriter}

object Generator {

  def gen(outDir: File): Unit = {
    ResCopy.copy(new File("src/main/web"), outDir)
    Data.pages.foreach(genPage(_, outDir))
    println("finished generation of taschenfahrrad in %s" format outDir.getCanonicalPath)
  }

  private def genPage(p: Page, outDir: File): Unit = {
    val fnam = Template.fileName(p)
    val f = new File(outDir, fnam)
    val pw = new PrintWriter(f, "UTF-8")
    pw.print(Template.htmlTemplate(p).trim())
    pw.close()
    println("wrote to %s" format f)
  }

}

