import FunctionalityActor.WaitAndRespond
import GUI.{PrintSomething, StartGui}
import akka.actor.{Actor, Props}

import scala.concurrent.duration.DurationInt
import scala.swing.{Button, FlowPanel, Frame, Label, event}

class GUI extends Actor {




 val frame =  new Frame {
    title = "Hello world"
    contents = new FlowPanel {
      contents += new Label("without Actor")
      contents += new Button("Print") {
        reactions += {
          case event.ButtonClicked(_) =>
            Thread.sleep(2.seconds.toMillis)
            context.self ! PrintSomething("Hello")
        }
      }
      contents += new Label("with Actor")
      contents += new Button("Print Async") {
        reactions += {
          case event.ButtonClicked(_) =>
            context.actorOf(Props(new FunctionalityActor)) ! WaitAndRespond(2.seconds, PrintSomething("Hello Async"))
        }
      }
    }
  }

  override def receive: Receive = {
    case StartGui => frame.open()
    case PrintSomething(msg) => println(msg)


  }
}
object GUI {
  case object StartGui
  case class PrintSomething(message: String)
}
