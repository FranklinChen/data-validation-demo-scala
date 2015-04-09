package com.franklinchen

object OptionWinner {
  case class Person(name: String) {
    //* Dummy implementation only.
    def bestFriend(): Option[Person] =
      if (name == "A")
        Some(personB)
      else
        None

    //* Dummy implementation only.
    def oldestSister(): Option[Person] =
      if (name == "B")
        Some(personC)
      else
        None

    //* Dummy implementation only.
    def youngestChild(): Option[Person] =
      if (name == "Y")
        Some(personZ)
      else
        None
  }

  val personA = Person("A")
  val personB = Person("B")
  val personC = Person("C")
  val personY = Person("Y")
  val personZ = Person("Z")

  /** Assume: bestFriend(), oldestSister(), youngestChild()
    each returns Option[Person] */
  def winner(person: Person): Option[Person] = for {
    friend <- person.bestFriend()
    sister <- friend.oldestSister()
    child <- sister.youngestChild()
  } yield child

  /** Assume: bestFriend(), oldestSister(), youngestChild()
    each returns Option[Person] */
  def unsweetWinner(person: Person): Option[Person] =
    person.bestFriend()   .flatMap( friend =>
    friend.oldestSister() .flatMap( sister =>
    sister.youngestChild()
    ))
}
