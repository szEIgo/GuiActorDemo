import GUI.StartGui
import akka.actor.{ActorSystem, Props}

import scala.swing._
object Init extends App{

  val as = ActorSystem()

  val guiActor = as.actorOf(Props(new GUI))


  guiActor ! StartGui


}
