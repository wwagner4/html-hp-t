package net.entelijan.tf

trait Command
object Generate extends Command
object Images extends Command
case class Error(message: String) extends Command

object Main {

  def parseCmd(args: Array[String]): Command = {
    if args.size == 0 then
      Error("No command give. use one of 'generate', 'images'")
    else Error("Not yet implemanted")
  }

  def main(args: Array[String]): Unit = {

    parseCmd(args) match {
      case Generate   => println("## generate")
      case Images     => println("## images")
      case Error(msg) => println(f"## error: $msg")
    }

    // val templ = Templs.tiles
    // Generator.gen(TfUtil.inTargetDir(s"gen-${templ.id}"), templ)

  }
}
