package io.github.vi_kas

import java.util
import java.util.AbstractMap

import org.apache.ignite.stream.StreamSingleTupleExtractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.{Logger, LoggerFactory}

class DefaultSingleTupleStreamExtractor
  extends StreamSingleTupleExtractor[ConsumerRecord[String, String], String, String] {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  override def extract(sinkRecord: ConsumerRecord[String, String]): AbstractMap.SimpleEntry[String, String] = {
    logger.info("Record:" + sinkRecord.value.toString)

    val key = sinkRecord.key().toString
    val value = sinkRecord.value().toString
    val entries = new util.HashMap[String, String]()
    entries.put(key, value)

    logger.info("Extracted Entry: " + entries)
    new AbstractMap.SimpleEntry[String, String](key, value)
  }
}