package net.entelijan.tf

sealed trait Command
object Generate extends Command
object Images extends Command
case class Error(message: String) extends Command
case class Help(message: String) extends Command

object Main {

  def parseCmd(args: Array[String]): Command = {
    val hlpMessage = """
    |Run one of the following commands:
    | - generate: Generates the homepage
    | - images: Creates an image overview
    """.stripMargin

    if args.size == 0 then
      Error("No command give. use one of 'generate', 'images'")
    else {
      val x = args(0).toLowerCase().trim()
      println(f"--- $x")
      if x.startsWith("gen") then Generate
      else if x.startsWith("ima") then Images
      else if x.startsWith("help") then Help(hlpMessage)
      else if x.startsWith("-h") then Help(hlpMessage)
      else if x.startsWith("--he") then Help(hlpMessage)
      else
        Error(f"Illegal command '${args(0)}'. use one of 'generate', 'images'")
    }
  }

  def main(args: Array[String]): Unit = {

    parseCmd(args) match {
      case Generate =>
        val templ = Templs.tiles
        Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)
      case Images     => ???
      case Help(msg)  => println(msg)
      case Error(msg) => println(f"Error: $msg")
    }

  }
}
