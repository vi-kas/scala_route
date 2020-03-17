package io.github.vi_kas.kafka.protocol

import java.util.UUID

import io.github.vi_kas.avro.order.OrderCreated

object KafkaProtocol {

  abstract class KafkaEvent

  case class OrderCreatedEvent(
                                id: String = UUID.randomUUID().toString,
                                title: String, price: Double, quantity: Int /*, eventTimestamp: LocalDate, timeout: Long*/) extends KafkaEvent

  import org.apache.avro.specific.SpecificRecord

  def modelToAVROConverter(event: KafkaEvent): SpecificRecord =
    event match {
      case OrderCreatedEvent(id, title, price, quantity) =>
        OrderCreated(id, title, price, quantity)
    }
}