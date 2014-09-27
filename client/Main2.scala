import dispatch._, Defaults._

import scala.concurrent.Await
import scala.concurrent.duration._

object Main2 {

  def main(args: Array[String]): Unit = try {
    val request =
      url("http://localhost:9000")
        .setMethod("POST")
        .setContentType("text/plain", "UTF-8")
        .setBody(Main.data)

    val timeout = 10.seconds

    (1 to 3).foreach { i =>
      Await.result(Http(request OK as.Bytes), timeout)
    }

    System.gc()

    Thread.sleep(3000)

    Main.time {
      val requests = Future.sequence(List.fill(100)(
        Http(request OK as.Bytes)
      ))

      Await.result(requests, timeout)
    }
  }finally{
    dispatch.Http.shutdown()
  }

}
