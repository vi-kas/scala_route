lazy val commonSettings = Seq(
  name := "scala_route",
  scalaVersion := "2.12.8",
  description := "scala_route_one",
  version := "0.1.0-simple-scala",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http"   % "10.1.10",
    "com.typesafe.akka" %% "akka-stream" % "2.5.23",
    "io.circe" %% "circe-core" % "0.11.1",
    "io.circe" %% "circe-generic" % "0.11.1",
    "io.circe" %% "circe-parser" % "0.11.1"
  ),
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfatal-warnings",
    "-Yno-adapted-args",
    "-Xfuture"
  )
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "core"
  )

lazy val usage = (project in file("usage"))
  .settings(
    commonSettings,
    name := "usage",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.3.0",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
      "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
      "org.flywaydb" % "flyway-core" % "6.0.4"
    )
  ) dependsOn core

lazy val root = (project in file("."))
  .settings(
    commonSettings,
  ) aggregate(core, usage)