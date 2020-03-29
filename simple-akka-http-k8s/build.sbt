lazy val commonSettings = Seq(
  name := "scala_route",
  scalaVersion := "2.12.8",
  description := "scala_route_one",
  version := "0.0.1",
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
    ),
    dockerfile in docker := {
      val artifact: File = assembly.value
      val artifactTargetPath = s"/app/${artifact.name}"

      new Dockerfile {
        from("openjdk:8-jre")
        add(artifact, artifactTargetPath)
        expose(8080)
        entryPoint("java", "-jar", artifactTargetPath)
      }
    },
    assemblyJarName in assembly := "orderService.jar",
    mainClass in assembly := Some("io.github.vi_kas.Server")
  )
  .enablePlugins(DockerPlugin)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
  ) aggregate usage