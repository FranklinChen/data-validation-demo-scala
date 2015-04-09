package com.franklinchen

import scalaz._
import Scalaz._

object ResultWinner {
  /** For illustration only. String errors are terrible practice. */
  case class MyError(message: String)

  case class Person(name: String) {
    //* Dummy implementation only.
    def bestFriend(): MyError \/ Person =
      if (name == "A")
        personB.right
      else
        MyError(s"$name has no best friend").left

    //* Dummy implementation only.
    def oldestSister(): MyError \/ Person =
      if (name == "B")
        personC.right
      else
        MyError(s"$name has no oldest sister").left

    //* Dummy implementation only.
    def youngestChild(): MyError \/ Person =
      if (name == "Y")
        personZ.right
      else
        MyError(s"$name has no youngest child").left
  }

  val personA = Person("A")
  val personB = Person("B")
  val personC = Person("C")
  val personY = Person("Y")
  val personZ = Person("Z")

  /** Assume: bestFriend(), oldestSister(), youngestChild()
    each returns MyError \/ Person */
  def winner(person: Person): MyError \/ Person = for {
    friend <- person.bestFriend()
    sister <- friend.oldestSister()
    child <- sister.youngestChild()
  } yield child
}
