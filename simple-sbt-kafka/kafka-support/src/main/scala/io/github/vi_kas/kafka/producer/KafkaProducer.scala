package io.github.vi_kas.kafka

import java.util.concurrent.TimeUnit

import com.typesafe.config.Config
import io.github.vi_kas.kafka.protocol.KafkaProtocol._
import io.github.vi_kas.kafka.protocol.SpecificRecordSerDe
import org.apache.kafka.clients.producer.{Producer, ProducerRecord}
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success, Try}

class KafkaProducer(appConfig: Config) {

  import akka.kafka.ProducerSettings
  private val logger = LoggerFactory.getLogger(getClass)

  import org.apache.kafka.common.serialization.StringSerializer
  val keySerializer: StringSerializer = new StringSerializer

  val serDe: SpecificRecordSerDe[AnyRef] =
    RecordSerDeBuilder().withSchemaRegUrl(appConfig.getString("kafka.schemareg.url")).createRecordSerDe()

  val producer: Producer[String, AnyRef] =
    ProducerSettings(appConfig.getConfig("akka.kafka.producer"), keySerializer, serDe)
      .withBootstrapServers(appConfig.getString("kafka.bootstrap.servers"))
      .createKafkaProducer()

  def handleEvent(topic: String, event: KafkaEvent): Unit = Try {
    logger.info(s"Sending event to Kafka: [$event]")

    val record = new ProducerRecord[String, AnyRef](topic, modelToAVROConverter(event))

    producer.send(record)
  } match {
    case Success(metadata) => {
      Try(metadata.get(60, TimeUnit.SECONDS)) match {
        case Success(result) => logger.info(s"Success sending [$event]. Metadata is [$result]")
        case Failure(e) => logger.info(s"Cannot obtain kafka send result for event [$event]", e)
      }
    }
    case Failure(e) => {
      logger.error(s"Unable to send [$event] to kafka topic", e)
      e.printStackTrace()
    }
  }

  Runtime.getRuntime.addShutdownHook(new Thread {
    override def run: Unit = {
      Try(producer.close())
    }
  })

}