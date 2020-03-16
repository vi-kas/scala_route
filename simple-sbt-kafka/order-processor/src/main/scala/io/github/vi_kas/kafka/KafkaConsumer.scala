package io.github.vi_kas.kafka

import akka.kafka.ConsumerSettings
import akka.kafka.scaladsl.Consumer
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

trait KafkaConsumer {

  lazy val appConfig: Config = ConfigFactory.load()
  private val logger = LoggerFactory.getLogger(getClass)
//
//  if(appConfig != null){
//    println(s"appConfig isn't null")
//  } else {
//    println(s"appConfig is null")
//  }

  lazy val consumerConfig: Config = appConfig.getConfig("akka.kafka.consumer")

  lazy val SCHEMA_REG_URL: String = appConfig.getString("kafka.schemareg.url")
  lazy val BOOTSTRAP_SERVERS: String = appConfig.getString("kafka.bootstrap.servers")
  lazy val GROUP_ID: String = appConfig.getString("kafka.consumer.groupId")

  lazy val serDe: SpecificRecordSerDe[AnyRef] =
    RecordSerDeBuilder().withSchemaRegUrl(SCHEMA_REG_URL).createRecordSerDe()

  def defaultSettings[V]: ConsumerSettings[String, AnyRef] = settingsWithStringKeySerDe(consumerConfig, serDe)

  def settingsWithStringKeySerDe[V](consumerConfig: Config,
                                    valueSerDe: SpecificRecordSerDe[V]): ConsumerSettings[String, V] =
    ConsumerSettings(consumerConfig, new StringDeserializer, valueSerDe)
      .withBootstrapServers(BOOTSTRAP_SERVERS)
      .withGroupId(GROUP_ID)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  def subscribe(topic: String): Consumer.Control
}