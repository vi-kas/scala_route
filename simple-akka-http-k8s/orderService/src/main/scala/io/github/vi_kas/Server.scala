package io.github.vi_kas

import com.typesafe.config.{Config, ConfigFactory}
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}

object Server extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: Materializer = ActorMaterializer()

  def config: Config = ConfigFactory.load()

  HttpService.run(config)
}