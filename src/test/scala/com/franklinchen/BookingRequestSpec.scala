package com.franklinchen

import scalaz._
import Scalaz._

import org.specs2._
import org.specs2.scalaz.ValidationMatchers

class BookingRequestSpec extends Specification
    with ValidationMatchers { def is = s2"""
  ${`Date is early and seats bad`}
  ${`All good`}
  """

  def `Date is early and seats bad` = {
    val now = Date(2)

    BookingRequest.make(
      now,
      Some("1"),
      Some(-3)
    ) must beFailing.like {
      case e =>
        e ==== NonEmptyList(
          BookingRequest.DateBefore(Date(1), Date(2)),
          BookingRequest.SeatsError(Seats.BadCount(-3))
        )
    }
  }

  def `All good` = {
    val now = Date(2)

    BookingRequest.make(
      now,
      Some("3"),
      Some(5)
    ) must beSuccessful
  }
}
