package com.simple

import akka.http.scaladsl.server.Route
import com.simple.data.DatabaseComponent
import com.simple.libsupport.CirceMarshallable
import com.simple.services._
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global

//https://www.innoq.com/en/blog/functional-service-in-scala/
trait Routes extends CirceMarshallable with DatabaseComponent

object Routes extends UserRoutes with UserServiceComponent {

  val driver: JdbcProfile = slick.jdbc.PostgresProfile
  val dbConfig: String = "pgconfe"

  override val userService: UserService = new UserServiceImpl
  override val repo: UserRepo = new UserRepo

  val routes: Route = userRoutes
}