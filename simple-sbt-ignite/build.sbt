lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  libraryDependencies += "com.typesafe" % "config" % "1.4.0",
  libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

lazy val igniteDependencies = Seq(
  "org.apache.ignite" % "ignite-core" % "2.8.0",
  "org.apache.ignite" % "ignite-indexing" % "2.8.0"
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name := "core",
    libraryDependencies ++= igniteDependencies
  )

lazy val distributedKVCache = (project in file("distributedKVCache"))
  .settings(
    commonSettings,
    name := "distributedKVCache"
  ) dependsOn core

lazy val ignitePersistence = (project in file("ignitePersistence"))
  .settings(
    commonSettings,
    name := "ignitePersistence",
    libraryDependencies += "org.postgresql" % "postgresql" % "42.2.11",
    libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.3",
  ) dependsOn core

lazy val igniteKafkaStreamer = (project in file("igniteKafkaStreamer"))
  .settings(
    commonSettings,
    name := "igniteKafkaStreamer",
    libraryDependencies += "org.apache.ignite" % "ignite-kafka" % "2.8.0"
  ) dependsOn core

lazy val root = (project in file("."))
  .settings(
    name := "simple-sbt-ignite",
    description := "Demonstrates apache-ignite usage in sbt project",
    commonSettings
  ) aggregate(core)