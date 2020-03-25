package io.github.vi_kas

import java.util

import org.apache.ignite.stream.StreamSingleTupleExtractor
import org.apache.kafka.connect.sink.SinkRecord
import org.slf4j.{Logger, LoggerFactory}

class DefaultSingleTupleStreamExtractor extends StreamSingleTupleExtractor[SinkRecord, String, String] {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  override def extract(sinkRecord: SinkRecord): util.Map[String, String] = {
    logger.info("SinkRecord:" + sinkRecord.value.toString)

    val key = sinkRecord.key().toString
    val value = sinkRecord.value().toString
    val entries = new util.HashMap[String, String]()
    entries.put(key, value)

    logger.info("Extracted Entry: " + entries)
    entries
  }
}