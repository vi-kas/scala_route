package io.github.vi_kas

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{AuthorizationFailedRejection, Directive, Route}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.github.vi_kas.jwt.{JwtConfig, JwtUtil}
import org.scalatest.WordSpec

class AkkaHttpRoutesSpec extends WordSpec with ScalatestRouteTest {

  val apiPrefix: Directive[Unit] = pathPrefix("foo")

  val unsecured: Route =
    apiPrefix {
      (post & entity(as[String])){str =>
        println("foo/unsecured called!")

        complete(StatusCodes.Created, str)
      }
    }

  // Here are the imports needed to make auth work!
  import io.github.vi_kas.auth.AuthenticationDirective._

  val securedRoute: Route =
    apiPrefix {
      (post & entity(as[String])){str =>
        println("foo/secured called!")

        secured() { x =>
          println(s"[secured req] - x: $x")

          complete(StatusCodes.Created, str)
        }
      }
    }

  def routes(secString: String): Route =
    secString match {
      case "secured" => securedRoute
      case "unsecured" => unsecured
      case other => complete(StatusCodes.InternalServerError, other + " Does not exist.")
    }

  "[Unsecured] POST /foo should return 201" in {

    Post("/foo") ~> routes("unsecured") ~> check {

      assert(response.status == StatusCodes.Created)
    }
  }

  "[Secured] POST /foo without token should be Rejected" in {

    Post("/foo") ~> routes("secured") ~> check {

      assert(rejections.nonEmpty)
      assert(rejection == AuthorizationFailedRejection)
    }
  }

  "[Secured] POST /foo with token should return Created" in {

    val config = JwtConfig.fromConfig()
    val hmacAlgo = JwtUtil.hmacAlgorithm

    assert(hmacAlgo.isSuccess)

    val token = JwtUtil.create(config.copy(sub = "LegitUser"), hmacAlgo.get)

    println(token)

    val header = RawHeader("SESSION_DATA", token.get)

    Post("/foo").addHeader(header) ~> routes("secured") ~> check {

      assert(response.status == StatusCodes.Created)
    }
  }

}