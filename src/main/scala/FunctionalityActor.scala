import FunctionalityActor.WaitAndRespond
import GUI.PrintSomething
import akka.actor.Actor

import scala.concurrent.duration.FiniteDuration

class FunctionalityActor extends Actor {


  override def receive: Receive = {
    case WaitAndRespond(wait, printSomething) =>
      Thread.sleep(wait.toMillis)
      context.parent ! printSomething
  }
}

object FunctionalityActor {
  case class WaitAndRespond(waitFor: FiniteDuration, printSomething: PrintSomething)
}
