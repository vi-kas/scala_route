package io.github.vi_kas.kafka

import akka.kafka.ConsumerSettings
import akka.kafka.scaladsl.Consumer
import com.typesafe.config.Config
import io.github.vi_kas.kafka.protocol.SpecificRecordSerDe
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

abstract class KafkaConsumer(appConfig: Config) {

  val consumerConfig: Config = appConfig.getConfig("akka.kafka.consumer")

  val (schema_reg_url, bootstrap_server, group_id) =
    (confString("kafka.schemareg.url"),
      confString("kafka.bootstrap.servers"),
      confString("kafka.consumer.groupId"))

  val serDe: SpecificRecordSerDe[AnyRef] =
    RecordSerDeBuilder().withSchemaRegUrl(schema_reg_url).createRecordSerDe()

  def defaultSettings[V]: ConsumerSettings[String, AnyRef] = settingsWithStringKeySerDe(consumerConfig, serDe)

  def settingsWithStringKeySerDe[V](consumerConfig: Config,
                                    valueSerDe: SpecificRecordSerDe[V]): ConsumerSettings[String, V] =
    ConsumerSettings(consumerConfig, new StringDeserializer, valueSerDe)
      .withBootstrapServers(bootstrap_server)
      .withGroupId(group_id)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  //local method
  private def confString(str: String): String = appConfig.getString(str)

  def subscribe(topic: String): Consumer.Control
}