
name := "simple-scala-alexa"
scalaVersion := "2.12.8"
description := "Simple example to learn alexa skills"
version := "0.0.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

val alexaVersion = "2.20.2"

lazy val askSdk = "com.amazon.alexa" % "ask-sdk" % alexaVersion
lazy val askSdkCore = "com.amazon.alexa" % "ask-sdk-core" % alexaVersion
lazy val askSdkApacheClient = "com.amazon.alexa" % "ask-sdk-apache-client" % alexaVersion

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.8.2"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.8.2"

libraryDependencies ++= Seq(askSdk, askSdkCore, askSdkApacheClient)