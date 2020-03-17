package io.github.vi_kas

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.stream.Materializer
import com.softwaremill.macwire._
import com.typesafe.config.{Config, ConfigFactory}
import io.github.vi_kas.kafka.{KafkaConsumer, KafkaConsumerAtMostOnce}
import io.github.vi_kas.services.{OrderProcessingService, OrderProcessingServiceImpl}
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.Future

class OrderProcessor private(config: Config, kafkaConsumer: KafkaConsumer) {

  private val logger = LoggerFactory.getLogger(getClass)

  def start(topics: String): Consumer.Control = {
    logger.info(s"Starting OrderProcessor")

    kafkaConsumer.subscribe(topics)
  }

  def stop(control: Consumer.Control): Future[Done] = {
    logger.info(s"Stopping OrderProcessor")

    control.shutdown()
  }
}

object OrderProcessor extends App {
  val logger: Logger = LoggerFactory.getLogger(getClass)

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: Materializer = Materializer(system)

  val config: Config = ConfigFactory.load()
  private val ORDERS_TOPIC = config.getString("kafka.topics.events.order")

  val orderProcessingService: OrderProcessingService = wire[OrderProcessingServiceImpl]
  val kConsumer: KafkaConsumer = wire[KafkaConsumerAtMostOnce]

  new OrderProcessor(config, kConsumer)
    .start(ORDERS_TOPIC)
}