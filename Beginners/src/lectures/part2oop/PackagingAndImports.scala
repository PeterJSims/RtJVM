package lectures.part2oop

import java.util.Date
import java.sql.{Date => Date2}

import playground.{Test1, Test2 => SecondTest}
import exercises.Writer

object PackagingAndImports extends App {
  // package members are accessable by their simple name
  val person = new Person("Peter", 34)

  // else import the package
  val writer = new Writer("Jorge Luis", "Borges", 1900)

  // or use the full name
  val calc = new exercises.Novel("Labyrinths", 1960, writer)


  // packages are hierarchical
  // matches folder structure


  // package object
  // one package object per package
  sayHello // No need to import because it's in package object (package.scala)

  // imports
  val test1 = Test1
  // import renamed up top
  val test2 = SecondTest


  // imports of the same name -- see imports for aliasing
  val date = new Date
//  val sqlDate = Date2

  // default imports
  // java.lang
  // scala - Int, Nothing, Function
  // scala.Predef = println, ???

}
