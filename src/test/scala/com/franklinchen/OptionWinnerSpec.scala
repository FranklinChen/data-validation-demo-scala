package com.franklinchen

import org.specs2._
import OptionWinner.{winner, personA}

class OptionWinnerSpec extends Specification { def is = s2"""
  ${`A has no winner`}
  """

  def `A has no winner` =
    winner(personA) must beNone
}
