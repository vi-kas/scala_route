lazy val commonSettings = Seq(
  name := "scala_route",
  scalaVersion := "2.12.8",
  description := "scala_route_one",
  version := "0.1.0-simple-scala",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

lazy val usage = (project in file("usage"))
  .settings(
    commonSettings,
    name := "usage",
    libraryDependencies ++= Seq(
      "com.softwaremill.macwire" %% "macros" % "2.3.2" % "provided",
      "com.typesafe.akka" %% "akka-http"   % "10.1.10",
      "com.typesafe.akka" %% "akka-stream" % "2.5.23",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
      "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.23",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.1.10"
    )
  )

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "core"
  ) dependsOn usage

lazy val root = (project in file("."))
  .settings(
    commonSettings,
  ) aggregate(usage, core)