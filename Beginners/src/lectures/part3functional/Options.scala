package lectures.part3functional

import scala.util.Random

//IF YOU MIGHT HAVE A NULL RESULT, USE OPTION()


object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  // Options are great for unsafe APIs and avoiding crashes

  // unsafe API example
  def unsafeMethod(): String = null

  //  val result = Some(unsafeMethod()) // WRONG because it could be Some(null)
  val result = Option(unsafeMethod()) // returns None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  // make something return Option to avoid nulls
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedMethod = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter on Options
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x < 4))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  val config: Map[String, String] = Map(
    // fetched from elsewhere - could fail1
    "host" -> "176.45.32.1",
    "port" -> "8080" // what if this was null
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val randomGenerator = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (randomGenerator.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (c != null)
      return c.connect
    else null
   */
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)


  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehension version
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}
