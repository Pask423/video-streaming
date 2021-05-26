import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http


object Runner extends App {

  val (host, port) = ("localhost", 8090)
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "akka-video-stream")
  Http().newServerAt(host, port).bind(Streamer.route)

}

