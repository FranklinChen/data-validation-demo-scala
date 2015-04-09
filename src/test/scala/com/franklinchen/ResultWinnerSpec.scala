package com.franklinchen

import org.specs2._
import org.specs2.scalaz.DisjunctionMatchers

import ResultWinner.{winner, personA, MyError}

class ResultWinnerSpec extends Specification
    with DisjunctionMatchers { def is = s2"""
  ${`A has no winner because C no youngest child`}
  """

  def `A has no winner because C no youngest child` =
    winner(personA) must beLeftDisjunction.like {
      case e => e ==== MyError("C has no youngest child")
    }
}
