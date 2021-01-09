package lectures.part3functional

object TuplesAndMaps extends App {

  // Tuples = finite ordered "list" structures - same as Python
  val aTuple = new Tuple2(2, "hello, Scala") // Tuple2[Int, String] = (Int,String)
  val anotherTuple = (2, "hello again") // same as python declaration too
  // can group up to 22 elements because they are grouped with Function types

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye, Java"))
  println(aTuple.swap)

  // Maps
  val aMap: Map[String, Int] = Map()

  val phonebook = Map("Jim" -> 555, "Daniel" -> 556).withDefaultValue(-1)
  // a -> is sugar for (a, b)
  // provide a default with withDefaultValue

  println(phonebook)
  println(phonebook.contains("Jim"))
  println(phonebook("Jim")) //exists
  println(phonebook("James")) // not found, default value
  println(phonebook.getOrElse("Jimothy", "Not found")) // not found, Else value

  // add a key value pair
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // MAPS ARE IMMUTABLE
  println(newPhonebook)

  // functionals on maps
  // map, flatmap, filter



  //USE .KEYS AND .VALUES BEFORE MAP/FILTER/FOREACH/ETC, OTHERWISE
  //RtJVM OUT OF DATE
  println(newPhonebook.map((pair) => pair._1.toLowerCase -> pair._2))
  println(newPhonebook.keys.filter(x => x.startsWith("J")))
  println(newPhonebook.values.map(num => num * 10))

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Peter", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
