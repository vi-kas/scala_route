package io.github.vi_kas

import java.util
import java.util.Properties

import org.apache.ignite.Ignition
import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
import org.apache.ignite.stream.StreamMultipleTupleExtractor
import org.apache.ignite.stream.kafka.KafkaStreamer
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.connect.sink.SinkRecord
import org.slf4j.{Logger, LoggerFactory}

object IgniteApp {

  /*
   *  Application entry point.
   */
  def main(args: Array[String]): Unit = {

    val logger: Logger = LoggerFactory.getLogger(getClass)

    val KAFKA_TOPIC = "simple.key.value.topic"

    val topics = new util.ArrayList[String]()
    topics.add(KAFKA_TOPIC)

    val kafkaProperties = new Properties()
    kafkaProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    kafkaProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ignite-kafka-consumer")
    kafkaProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new StringDeserializer().getClass.getName)
    kafkaProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new StringDeserializer().getClass.getName)

    val kafkaStreamer: KafkaStreamer[String, String] = new KafkaStreamer[String, String]()
    kafkaStreamer.setConsumerConfig(kafkaProperties)
    logger.info(s"KafkaStreamer created with kafkaConfig: ${kafkaProperties}")

    val simpleCacheConfiguration: CacheConfiguration[String, String] =
      new CacheConfiguration[String, String]("simple-ignite-kafka-cache")

    val igniteConfiguration: IgniteConfiguration =
      new IgniteConfiguration().setCacheConfiguration(simpleCacheConfiguration)

    val ignite = Ignition.start(igniteConfiguration)
    logger.info(s"Ignite cache started with config: $igniteConfiguration")

    val igniteStreamer = ignite.dataStreamer("simple-ignite-kafka-cache")
    igniteStreamer.allowOverwrite(true)
    logger.info(s"IgniteStreamer created: $igniteStreamer")

    kafkaStreamer.setIgnite(ignite)
    kafkaStreamer.setTopic(topics)
    kafkaStreamer.setThreads(2)
//    kafkaStreamer.setSingleTupleExtractor(new DefaultSingleTupleStreamExtractor)

    logger.info(s"Starting kafkaStreamer ...")
    kafkaStreamer.start()

    Thread.sleep(10)

    logger.info(s"Stopping kafkaStreamer ...")
    kafkaStreamer.stop()
    igniteStreamer.close()
  }
}