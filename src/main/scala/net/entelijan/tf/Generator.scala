package net.entelijan.tf

import java.io.{File, PrintWriter}

object Generator {

  def gen(outDir: File, templ: Templ): Unit = {
    ResCopy.copy(new File("src/main/web/common"), outDir)
    ResCopy.copy(new File(s"src/main/web/${templ.id}"), outDir)
    Data.pages.foreach(genPage(_, templ, outDir))
    println(s"finished generation of taschenfahrrad in ${outDir.getCanonicalPath}")
  }

  private def genPage(p: Page, templ: Templ, outDir: File): Unit = {
    val hpages = templ.pages
    for (hp <- hpages) {
      val fnam = hp.fileName(p)
      val f = new File(outDir, fnam)
      val pw = new PrintWriter(f, "UTF-8")
      pw.print(hp.html(p).trim())
      pw.close()
      println(s"wrote to $f")
    }
  }

}

