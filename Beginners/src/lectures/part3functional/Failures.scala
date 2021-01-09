package lectures.part3functional

import scala.util.{Failure, Random, Success, Try}

//IF YOU MIGHT THROW AN EXCEPTION, USE TRY()


object Failures extends App {

  // we could create success and failure explicitly
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("FAILURE"))

  println(aSuccess)
  println(aFailure)

  // TRY companion object does the above for us
  def unsafeMethod(): String = throw new RuntimeException("UH OH")

  //  unsafeMethod() -- like expected crashes program called directly

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // Doesn't crash, even though still RuntimeException

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw is in here
  }

  // TRY utilities
  println(potentialFailure.isSuccess) // also isFailure

  // orElse
  def backupMethod(): String = "valid result"

  def safeChaining = Try(unsafeMethod()).orElse(Try(backupMethod()))

  println(safeChaining)

  // IF you design the API
  // Wrap your computations in Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  //map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10)) // FILTER CAN TURN SUCCESS INTO FAILURE BUT STILL WON'T THROW

  //for-comprehensions

  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))

  possibleHTML.foreach(renderHTML)

  //OR this shorthand way
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // ** FOR-COMPREHENSION VERSION **

  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

}
