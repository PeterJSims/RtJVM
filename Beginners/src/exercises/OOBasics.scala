package exercises

object OOBasics extends App {

}

class Writer(firstName: String, lastName: String, val yearOfBirth: Int) {
  def fullName(): String = s"${this.firstName} ${this.lastName}"

}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = this.yearOfRelease - this.author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean =
    this.author == author

  def copy(newYear: Int): Novel = new Novel(this.name, newYear, this.author)
}


class Counter(val count: Int) {
  def giveCount(): Int = this.count

  def inc(): Counter = new Counter(this.count + 1)

  def dec(): Counter = new Counter(this.count - 1)

  def inc(n: Int): Counter = new Counter(this.count + n)

  def dec(n: Int): Counter = new Counter(this.count - n)


}
