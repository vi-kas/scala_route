package com.simple

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.simple.Routes._
import com.simple.data.migration.FlywayService
import com.typesafe.config.Config

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

class HttpService private (config: Config)(implicit system: ActorSystem, materializer: Materializer) {

  implicit def executor: ExecutionContextExecutor = system.dispatcher
  val logger: LoggingAdapter = Logging(system, getClass)

  //Migration of Database from resources/db/migration takes place
  new FlywayService(config).migrateDatabaseSchema()

  val bindingFuture: Future[Http.ServerBinding] =
    Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))

  bindingFuture.onComplete{
    case Success(bound) =>
      logger.info(s"Server Started: ${bound.localAddress.getHostString}")
    case Failure(e) =>
      logger.error(s"Server could not start: ${e.getMessage}")
      system.terminate()
  }
}

object HttpService {

  def run(config: Config)(implicit system: ActorSystem, materializer: Materializer) =
    new HttpService(config)
}