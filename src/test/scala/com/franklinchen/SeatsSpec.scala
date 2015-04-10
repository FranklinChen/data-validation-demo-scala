package com.franklinchen

import scalaz._
import Scalaz._

import org.specs2._
import org.specs2.scalaz.DisjunctionMatchers

class SeatsSpec extends Specification
    with DisjunctionMatchers { def is = s2"""
  ${`No zero seats`}
  """

  def `No zero seats` =
    Seats.make(0) must beLeftDisjunction.like {
      case e => e ==== Seats.BadCount(0)
    }
}
