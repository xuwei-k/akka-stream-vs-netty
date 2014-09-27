import scalaj.http.{Http, HttpOptions}
import scala.util.Random

object Main {

  val data: String = Random.alphanumeric.take(1024 * 1024 * 5).mkString

  def main(args: Array[String]): Unit = {
    val request =
      Http.postData("http://localhost:9000", data.getBytes)
      .option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(10000))
      .header("Content-Type", "text/plain")


    (1 to 3).foreach{ _ =>
      request.asString
    }

    Thread.sleep(3000)

    println("start")
    
    time{
      (1 to 100).foreach{ i =>
        print(i + " ")
        request.asString
      }
    }

  }

  def time[A](a: => A): A = {
    System.gc
    val s = System.nanoTime
    val r = a
    println((System.nanoTime - s) / 1000000.0)
    r
  }

}
