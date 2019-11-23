package net.entelijan.tf

import java.io.{File, PrintWriter}

object Generator {

  def gen(outDir: File, templ: Templ): Unit = {
    ResCopy.copy(new File("src/main/web"), outDir)
    Data.pages.foreach(genPage(_, templ, outDir))
    println(s"finished generation of taschenfahrrad in ${outDir.getCanonicalPath}")
  }

  private def genPage(p: Page, templ: Templ, outDir: File): Unit = {
    val fnam = templ.fileName(p)
    val f = new File(outDir, fnam)
    val pw = new PrintWriter(f, "UTF-8")
    pw.print(templ.html(p).trim())
    pw.close()
    println(s"wrote to $f")
  }

}

