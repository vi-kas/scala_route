package io.github.vi_kas

import java.util
import java.util.{AbstractMap, Properties}

import org.apache.ignite.{Ignite, IgniteCache, IgniteDataStreamer, Ignition}
import org.apache.ignite.configuration.{CacheConfiguration, IgniteConfiguration}
import org.apache.ignite.stream.kafka.KafkaStreamer
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.connect.sink.SinkRecord
import org.slf4j.{Logger, LoggerFactory}


// https://github.com/apache/ignite/blob/master/modules/kafka/src/test/java/org/apache/ignite/stream/kafka/KafkaIgniteStreamerSelfTest.java

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

    val ignite: Ignite = Ignition.start(igniteConfiguration)
    logger.info(s"Ignite cache started with config: $igniteConfiguration")

    val igniteStreamer: IgniteDataStreamer[String, String] = ignite.dataStreamer("simple-ignite-kafka-cache")
    igniteStreamer.allowOverwrite(true)
    logger.info(s"IgniteStreamer created: $igniteStreamer")

    kafkaStreamer.setIgnite(ignite)
    kafkaStreamer.setTopic(topics)
    kafkaStreamer.setThreads(2)
    kafkaStreamer.setStreamer(igniteStreamer)
    kafkaStreamer.setSingleTupleExtractor(new DefaultSingleTupleStreamExtractor)

    val cache: IgniteCache[String, String] = ignite.cache("simple-ignite-kafka-cache")

    logger.info(s"Starting kafkaStreamer ...")
    kafkaStreamer.start()

    cache.get("key1")

    logger.info(s"Waiting for 10 seconds!")
    Thread.sleep(10000)

    logger.info(s"Stopping kafkaStreamer ...")
    kafkaStreamer.stop()
    igniteStreamer.close()
  }
}