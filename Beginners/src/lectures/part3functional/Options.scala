package lectures.part3functional

import scala.util.Random

//IF YOU MIGHT HAVE A NULL RESULT, USE OPTION()


object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // Working with unsafe APIs
  def unsafeMethod(): String = null

  //  val result = Some(unsafeMethod()) // WRONG BECAUSE unsafeMethod COULD RETURN NULL
  // SOME() SHOULD *NEVER* RETURN A NULL VALUE
  val result = Option(unsafeMethod()) // Option provides Some or None depending
  println(result)

  // chained methods
  def backupMethod(): String = "result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Designing unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterBackupMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE -> COULD GRAB NULL

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ % 3 == 0))
  println(myFirstOption.flatMap(x => Option(x * 10)))


  // quick exercise
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "8080"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // establish a connection - if so, print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h && p){
      return Connection.apply(h,p)
    }
    return null

    IMPERATIVE VERSION OF BELOW
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
    if (c) {
      return c.connect
    }
    return null
   */
  val connectionStatus = connection.map(_.connect)
  // if (!connectionStatus) println(None) else println(Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if (status) {
      println(status
      }
   */
  connectionStatus.foreach(println)

  // chained way
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)


  // for-comprehension version
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port )
  } yield connection.connect

}
