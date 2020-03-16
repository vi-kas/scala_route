package io.github.vi_kas.kafka

import java.util

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.serializers.{KafkaAvroDeserializer, KafkaAvroSerializer}

class SpecificRecordSerDeAvroImpl(schemaRegistryClient: SchemaRegistryClient, config: java.util.Map[String, _])
  extends SpecificRecordSerDe[AnyRef] {

  val avroSerializer: KafkaAvroSerializer =
    new KafkaAvroSerializer(schemaRegistryClient, config)

  val avroDeserializer: KafkaAvroDeserializer =
    new KafkaAvroDeserializer(schemaRegistryClient, config)

  override def serialize(topic: String, data: AnyRef): Array[Byte] =
    avroSerializer.serialize(topic, data)

  override def deserialize(topic: String, data: Array[Byte]): AnyRef =
    avroDeserializer.deserialize(topic, data)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit =
    avroDeserializer.configure(configs, isKey)

  override def close(): Unit = {
    avroSerializer.close()
    avroDeserializer.close()
  }

}