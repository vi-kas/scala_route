package io.github.vi_kas.kafka

import akka.actor.ActorSystem
import akka.kafka.Subscriptions
import akka.kafka.scaladsl.Consumer
import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.Config
import org.slf4j.LoggerFactory

class KafkaConsumerAtMostOnce(
                               config: Config
                             )(implicit actorSystem: ActorSystem, mat: Materializer) extends KafkaConsumer {

  override val appConfig: Config = config
  private val logger = LoggerFactory.getLogger(getClass)

  def subscribe(topic: String): Consumer.Control = {
    logger.info(s"subscribing to topic: $topic")

    Consumer
      .atMostOnceSource(defaultSettings, Subscriptions.topics(topic))
      .map(_.value())
      .to(Sink.foreach(event => logger.info(s"Received a Kafka message: ${event.toString}")))
      .run()
  }

}