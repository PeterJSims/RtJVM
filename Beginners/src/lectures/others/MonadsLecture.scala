package lectures.others

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

object MonadsLecture extends App {

  case class SafeValue[+T](private val internalValue: T) { // "constructor" = pure or unit
    def get: T = synchronized {
      //imagine this does something interesting
      internalValue
    }

    def flatMap[S](transformer: T => SafeValue[S]): SafeValue[S] = synchronized { // bind or flatMap
      transformer(internalValue)
    }
  }

  // "external" API
  def giveSafeValue[T](value: T): SafeValue[T] = SafeValue(value)

  val safeString: SafeValue[String] = giveSafeValue("Scala is great")
  // extract
  val string = safeString.get
  // transform
  val upperString = string.toUpperCase()
  // wrap
  val upperSafeString = SafeValue(upperString)
  // ETW - extract/transform/wrap pattern is very common

  // but we could also write something to do it in one go
  val upperSafeString2 = safeString.flatMap(s => SafeValue(s.toUpperCase))


  // Examples

  //Ex 1: Census
  case class Person(firstName: String, lastName: String) {
    assert(firstName != null && lastName != null)
  }

  // census API
  def getPerson(firstName: String, lastName: String): Person =
    if (firstName != null) {
      if (lastName != null) {
        Person(firstName, lastName)
      } else {
        null
      }
    } else {
      null
    }

  //better way
  def getPersonBetter(firstName: String, lastName: String): Option[Person] =
    Option(firstName).flatMap {
      fName =>
        Option(lastName).flatMap(
          lName => Option(Person(fName, lName))
        )
    }

  def getPersonBest(firstName: String, lastName: String): Option[Person] =
    for {
      fName <- Option(firstName)
      lName <- Option(lastName)
    } yield Person(fName, lName)

  // Example 2: asynchronous fetches

  case class User(id: String)

  case class Product(sku: String, price: Double)

  def getUser(url: String): Future[User] = Future {
    User("peter") // sample implementation
  }

  def getLastOrder(userId: String): Future[Product] = Future {
    Product("123-456", 99.99) // sample
  }

  val petersUrl = "my.store.com/users/peter"

  // ETW futures
  getUser(petersUrl).onComplete({
    case Success(User(id)) =>
      val lastOrder = getLastOrder(id)
      lastOrder.onComplete({
        case Success(Product(sku, price)) =>
          val taxIncludedPrice = price * 1.19
        // pass it on - ie send an email
      })
  })
  val betterTaxIncludedPrice: Future[Double] = getUser(petersUrl)
    .flatMap(user => getLastOrder(user.id))
    .map(_.price * 1.19)

  val bestTaxIncludedPrice: Future[Double] = for {
    user <- getUser(petersUrl)
    product <- getLastOrder(user.id)
  } yield product.price * 1.19

  // Properties of monads

  // prop 1 - "left identity"
  def twoConsecutive(x: Int) = List(x, x + 1)

  twoConsecutive(3) // List(3,4)
  List(3).flatMap(twoConsecutive) // List(3,4)
  // Monad(x).flatMap(f) == f(x)

  // prop 2 - "right identity"
  List(1, 2, 3).flatMap(x => List(x))
  // Monad(x).flatMap(y => Monad(y)) <- USELESS, returns Monad(x)

  // prop 3 - ETW-ETW
  val numbers = List(1, 2, 3)

  val incrementer = (x: Int) => List(x, x + 1)
  val doubler = (x: Int) => List(x, x * 2)
  println(
    numbers.flatMap(incrementer).flatMap(doubler) ==
      numbers.flatMap(x => incrementer(x).flatMap(doubler))
  )


  // List(1, 2, 2, 4,   2, 4, 3, 6,   3, 6, 4, 8)
  /*
    List(
      incrementer(1).flatMap(doubler) - 1,2,2,4
      incrementer(2).flatMap(doubler) - 2,4,3,6
      incrementer(3).flatMap(doubler) - 3,6,4,8
    )

    Monad(v).flatMap(f).flatMap(g) == Monad(v).flatMap(x => f(x).flatmap(g))
   */
}
