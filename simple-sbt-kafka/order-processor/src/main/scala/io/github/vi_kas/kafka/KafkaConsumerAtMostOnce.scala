package io.github.vi_kas.kafka

import akka.kafka.Subscriptions
import akka.kafka.scaladsl.Consumer
import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.Config
import io.github.vi_kas.services.OrderProcessingService
import org.slf4j.{Logger, LoggerFactory}

class KafkaConsumerAtMostOnce(
                               config: Config,
                               processingService: OrderProcessingService
                             )(implicit mat: Materializer) extends KafkaConsumer(config) {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def subscribe(topic: String): Consumer.Control = {
    logger.info(s"subscribing to topic: $topic")

    import processingService._

    Consumer
      .atMostOnceSource(defaultSettings, Subscriptions.topics(topic))
      .map(_.value())
      .to(Sink.foreach(processOrderEvent))
      .run()
  }

}