lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  version := "0.1.0-simple-scala",
  libraryDependencies ++= Seq(
    "com.softwaremill.macwire" %% "macros" % "2.3.2" % "provided",
    "org.scalactic" %% "scalactic" % "3.0.8",
    "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  )
)


lazy val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.10",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.23",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.10"
)


lazy val jwt_lib_ext = (project in file("jwt_lib"))
  .settings(
    name := "jwt_lib",
    commonSettings,
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.0",
      "com.auth0" % "java-jwt" % "3.9.0",
      "com.auth0" % "jwks-rsa" % "0.11.0",
      "org.bouncycastle" % "bcprov-jdk15on" % "1.59"
    )
  )


lazy val akka_auth_ext = (project in file("akka_auth"))
  .settings(
    name := "akka_auth",
    commonSettings,
    libraryDependencies ++= akkaDependencies
  ) dependsOn jwt_lib_ext


lazy val akka_http_auth_app = (project in file("akka_http_auth_app"))
  .settings(
    name := "akka_http_auth_app",
    commonSettings
  ) dependsOn akka_auth_ext


lazy val root = (project in file("."))
  .settings(
    name := "akka-http-auth-demo",
    description := "akka-http-auth-demo",
    commonSettings
  )