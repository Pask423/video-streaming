name := "video-streaming"

scalaVersion := "2.13.5"

organization := "pask-software"

version := "1.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % "2.6.14",
  "com.typesafe.akka" %% "akka-stream" % "2.6.14",
  "com.typesafe.akka" %% "akka-http" % "10.2.4"
)
