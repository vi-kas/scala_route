package io.github.vi_kas.kafka

import io.confluent.kafka.schemaregistry.client.{CachedSchemaRegistryClient, SchemaRegistryClient}
import io.github.vi_kas.kafka.protocol.{SpecificRecordSerDe, SpecificRecordSerDeAvroImpl}

object RecordSerDeBuilder {

  def apply(capacity: Int = 1024,
            schemaRegUrl: String = "http://0.0.0.0:8081",
            isAutoRegisterSchema: Boolean = true,
            schemaRegistryClient: SchemaRegistryClient = null): RecordSerDeBuilder =
    new RecordSerDeBuilder(capacity, schemaRegUrl, isAutoRegisterSchema, schemaRegistryClient, createRecordSerDe)

  def createRecordSerDe(builder: RecordSerDeBuilder): SpecificRecordSerDe[AnyRef] = {
    import avro.shaded.com.google.common.collect.ImmutableMap

    val schemaClient =
      if (builder.schemaRegistryClient != null) builder.schemaRegistryClient
      else new CachedSchemaRegistryClient(builder.schemaRegUrl, builder.capacity)

    new SpecificRecordSerDeAvroImpl(schemaClient,
      ImmutableMap.of("specific.avro.reader", true, "auto.register.schema", builder.isAutoRegisterSchema, "schema.registry.url", builder.schemaRegUrl))
  }

}

class RecordSerDeBuilder(val capacity: Int = 1024,
                         val schemaRegUrl: String,
                         val isAutoRegisterSchema: Boolean,
                         val schemaRegistryClient: SchemaRegistryClient,
                         val factorySettings: RecordSerDeBuilder => SpecificRecordSerDe[AnyRef]) {

  def withCapacity(cap: Int): RecordSerDeBuilder = copy(capacity = cap)

  def withSchemaRegUrl(url: String): RecordSerDeBuilder = copy(schemaRegUrl = url)

  def withAutoRegisterSchema(flag: Boolean): RecordSerDeBuilder = copy(isAutoRegisterSchema = flag)

  def withFactorySettings(settings: RecordSerDeBuilder => SpecificRecordSerDe[AnyRef]): RecordSerDeBuilder =
    copy(factorySettings = settings)

  private def copy(
                    capacity: Int = capacity,
                    schemaRegUrl: String = schemaRegUrl,
                    isAutoRegisterSchema: Boolean = isAutoRegisterSchema,
                    schemaRegistryClient: SchemaRegistryClient = schemaRegistryClient,
                    factorySettings: RecordSerDeBuilder => SpecificRecordSerDe[AnyRef] = factorySettings
                  ): RecordSerDeBuilder =
    new RecordSerDeBuilder(capacity, schemaRegUrl, isAutoRegisterSchema, schemaRegistryClient, factorySettings)


  def createRecordSerDe(): SpecificRecordSerDe[AnyRef] = factorySettings.apply(this)

}