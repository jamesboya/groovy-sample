import sbt._
import Keys._

object HelloPlugin extends AutoPlugin {
    override lazy val projectSettings = Seq(commands += helloCommand)

    lazy val helloCommand =
        Command.command("hello") { (state: State) =>
            println("Hello from hello sbt plugin")
            state
        }
}