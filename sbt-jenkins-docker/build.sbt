lazy val commonSettings = Seq(
  name := "scala_route",
  scalaVersion := "2.12.8",
  description := "scala_route_one",
  version := "0.1.0-simple-scala",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "core"
  )

lazy val usage = (project in file("usage"))
  .settings(
    commonSettings,
    name := "usage"
  ) dependsOn core

lazy val root = (project in file("."))
  .settings(
    commonSettings,
  ) aggregate(usage, core)