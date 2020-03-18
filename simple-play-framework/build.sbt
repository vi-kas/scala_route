
name := "simple-play-framework"
version := "0.1.0-simple-scala"
scalaVersion := "2.12.8"

enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.softwaremill.macwire" %% "macros" % "2.3.2" % "provided"
)

lazy val root = (project in file("."))