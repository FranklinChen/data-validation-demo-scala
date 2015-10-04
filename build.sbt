name := "data-validation-demo"

organization := "com.franklinchen"

organizationHomepage := Some(url("http://franklinchen.com/"))

homepage := Some(url("http://github.com/FranklinChen/data-validation-demo"))

startYear := Some(2015)

description := "Data validation demo in Scala"

version := "1.0.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % Test,
  "org.specs2" %% "specs2-core" % "3.6.4" % "test",
  "org.specs2" %% "specs2-scalacheck" % "3.6.4" % "test",
  "org.typelevel" %% "scalaz-specs2" % "0.4.0" % Test
)

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

doctestSettings

doctestWithDependencies := false

doctestTestFramework := DoctestTestFramework.Specs2
