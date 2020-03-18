lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  version := "0.1.0-simple-scala",
  libraryDependencies ++= Seq(
    "com.softwaremill.macwire" %% "macros" % "2.3.2" % "provided",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalactic" %% "scalactic" % "3.0.8",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  )
)

lazy val AkkaVersion = "2.6.1"
lazy val AkkaHttpVersion = "10.1.10"

lazy val akkaDependencies = Seq(
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
  "io.confluent" % "kafka-streams-avro-serde" % "4.1.0",
)

lazy val kafkaSupport = (project in file("kafka-support"))
  .settings(
    commonSettings,
    name := "kafkaSupport",
    libraryDependencies ++= akkaDependencies ++ kafkaDependencies,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "1.1.0" exclude("org.slf4j","slf4j-log4j12")
  )
  .enablePlugins(SbtAvrohugger)
  .settings(
    avroSpecificScalaSource in Compile := (sourceDirectory in Compile).value / "scala",
    sourceGenerators in Compile += (avroScalaGenerateSpecific in Compile).taskValue
  )

lazy val orderProcessor = (project in file("order-processor"))
  .settings(
    commonSettings,
    name := "orderProcessor",
    libraryDependencies ++= akkaDependencies ++ kafkaDependencies,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "1.1.0" exclude("org.slf4j","slf4j-log4j12")
  )
  .enablePlugins(SbtAvrohugger)
  .settings(
    avroSpecificScalaSource in Compile := (sourceDirectory in Compile).value / "scala",
    sourceGenerators in Compile += (avroScalaGenerateSpecific in Compile).taskValue
  ) dependsOn kafkaSupport

lazy val ordersAPI = (project in file("order-api"))
  .settings(
    commonSettings,
    name := "ordersAPI",
    libraryDependencies ++= akkaDependencies ++ kafkaDependencies,
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "1.1.0" exclude("org.slf4j","slf4j-log4j12")
  ) dependsOn kafkaSupport

lazy val root = (project in file("."))
  .settings(
    name := "simple-sbt-kafka",
    commonSettings
  )