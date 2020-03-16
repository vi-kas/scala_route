package io.github.vi_kas.kafka

import akka.kafka.ConsumerSettings
import com.typesafe.config.Config
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

trait KafkaConsumer {

  val appConfig: Config
  private val logger = LoggerFactory.getLogger(getClass)

  private val consumerConfig: Config = appConfig.getConfig("akka.kafka.consumer")

  private val SCHEMA_REG_URL: String = appConfig.getString("kafka.schemareg.url")
  private val BOOTSTRAP_SERVERS: String = appConfig.getString("kafka.bootstrap.servers")
  private val GROUP_ID: String = appConfig.getString("kafka.consumer.groupId")

  val serDe: SpecificRecordSerDe[AnyRef] =
    RecordSerDeBuilder().withSchemaRegUrl(SCHEMA_REG_URL).createRecordSerDe()

  def defaultSettings[V]: ConsumerSettings[String, AnyRef] = settingsWithStringKeySerDe(consumerConfig, serDe)

  def settingsWithStringKeySerDe[V](consumerConfig: Config,
                                    valueSerDe: SpecificRecordSerDe[V]): ConsumerSettings[String, V] =
    ConsumerSettings(consumerConfig, new StringDeserializer, valueSerDe)
      .withBootstrapServers(BOOTSTRAP_SERVERS)
      .withGroupId(GROUP_ID)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
}