lazy val commonSettings = Seq(
  name := "scala_route",
  scalaVersion := "2.12.8",
  description := "scala_route_one",
  version := "0.1.0-simple-scala",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

val alexaVersion = "2.20.2"


lazy val askSdk = "com.amazon.alexa" % "ask-sdk" % alexaVersion
lazy val askSdkCore = "com.amazon.alexa" % "ask-sdk-core" % alexaVersion
lazy val askSdkApacheClient = "com.amazon.alexa" % "ask-sdk-apache-client" % alexaVersion

lazy val usage = (project in file("usage"))
  .settings(
    commonSettings,
    name := "usage",
    libraryDependencies ++= Seq(askSdk, askSdkCore, askSdkApacheClient)
  ) dependsOn core

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "core"
  )

lazy val root = (project in file("."))
  .settings(
    commonSettings,
  ) aggregate(usage, core)