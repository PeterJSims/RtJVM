package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 50
  val y = 51
  println(x.getClass)
  println(y.getClass)

  //   VALs are immutable, VARs are mutable (const vs let in JS)
  var z = 52
  z = 49

  val aString: String = "Yup"
  val anotherString = "Yea"

  // Follows Java syntax
  val aBoolean: Boolean = false // lowercase like java
  val aChar: Char = 'a' // single quotes like java
  val anInt: Int = x
  val aShort: Short = 9999
  val alOng: Long = 2342432423423413432L // provide L for long
  val aFloat: Float = 2.0f // provide f for float
  val aDouble: Double = 2.0 // without f for double

  var aVariable = 4
  aVariable = 5
  aVariable = 'c' //

}
