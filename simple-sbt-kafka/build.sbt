lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  version := "0.1.0-simple-scala",
  libraryDependencies ++= Seq(
    "com.softwaremill.macwire" %% "macros" % "2.3.2" % "provided",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "org.scalactic" %% "scalactic" % "3.0.8",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  )
)

lazy val AkkaVersion = "2.6.1"
lazy val AkkaHttpVersion = "10.1.10"

lazy val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http"   % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion
)

lazy val kafkaDependencies = Seq(
  "org.apache.avro" % "avro" % "1.8.2",
  "org.apache.kafka" % "kafka-clients" % "2.1.1",
  "io.confluent" % "kafka-avro-serializer" % "3.2.1",
  "com.typesafe.akka" %% "akka-stream-kafka" % "1.1.0",
  "io.confluent" % "kafka-streams-avro-serde" % "4.1.0",
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
    libraryDependencies ++= akkaDependencies ++ kafkaDependencies
  )
  .enablePlugins(SbtAvrohugger)
  .settings(
    avroSpecificScalaSource in Compile := (sourceDirectory in Compile).value / "scala",
    sourceGenerators in Compile += (avroScalaGenerateSpecific in Compile).taskValue
  ) dependsOn core

lazy val root = (project in file("."))
  .settings(
    name := "simple-sbt-kafka",
    commonSettings,
  )

// avroSpecificScalaSource in Compile := (sourceDirectory in Compile).value,
// sourceGenerators in Compile += (avroScalaGenerateSpecific in Compile).taskValue