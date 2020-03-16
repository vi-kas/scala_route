package io.github.vi_kas.kafka

import org.apache.kafka.common.serialization.{Deserializer, Serializer}

trait SpecificRecordSerDe[T] extends Serializer[T] with Deserializer[T]