import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, get, optionalHeaderValueByName, path, _}
import akka.http.scaladsl.server.Route
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

import java.io.File
import scala.concurrent.Future

object Streamer {

  val route: Route =
    path("api" / "files" / "default") {
      get {
        optionalHeaderValueByName("Range") {
          case None => complete(StatusCodes.RangeNotSatisfiable)
          case Some(range) => complete(stream(range))
        }
      }
    }

  private def stream(rangeHeader: String): HttpResponse = {
    val path = "path/to/file"
    val file = new File(path)
    val fileSize = file.length()

    val range = rangeHeader.split("=")(1).split("-")
    val start = range(0).toInt
    val end = fileSize - 1

    val headers = List(
      RawHeader("Content-Range", s"bytes $start-$end/$fileSize"),
      RawHeader("Accept-Ranges", s"bytes")
    )

    val fileSource: Source[ByteString, Future[IOResult]] = FileIO.fromPath(file.toPath, 1024, start)
    val responseEntity = HttpEntity(MediaTypes.`video/mp4`, fileSource)
    HttpResponse(StatusCodes.PartialContent, headers, responseEntity)
  }

}
