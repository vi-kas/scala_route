package io.github.vi_kas

import akka.Done
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.kafka.scaladsl.Consumer
import akka.stream.Materializer
import com.softwaremill.macwire._
import com.typesafe.config.{Config, ConfigFactory}
import io.github.vi_kas.kafka.{KafkaConsumer, KafkaConsumerAtMostOnce}
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContextExecutor, Future}

class OrderProcessor private(config: Config, kafkaConsumer: KafkaConsumer)(implicit system: ActorSystem, materializer: Materializer) {

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

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext = system.dispatcher

  private val ORDERS_TOPIC = "simple.order"

  implicit def executor: ExecutionContextExecutor = system.dispatcher
  val logger: LoggingAdapter = Logging(system, getClass)

  def config: Config = ConfigFactory.load()

  val kConsumer: KafkaConsumer = wire[KafkaConsumerAtMostOnce]

  val orderProcessor = new OrderProcessor(config, kConsumer)
  orderProcessor.start(ORDERS_TOPIC)
}