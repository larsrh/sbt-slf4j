organization := "com.vast"

name := "sbt-slf4j"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.scala-sbt" % "logging" % "0.13.5" % "provided"
)