package io.github.vi_kas.services

import com.typesafe.config.Config
import io.github.vi_kas.avro.order.OrderCreated
import org.slf4j.LoggerFactory

trait OrderProcessingService {

  def processOrderEvent(kafkaEvent: AnyRef): Unit
}

class OrderProcessingServiceImpl(config: Config)
  extends OrderProcessingService {

  private val logger = LoggerFactory.getLogger(getClass)

  override def processOrderEvent(kafkaEvent: AnyRef): Unit = {
    logger.info(s"processOrderEvent Called")

    kafkaEvent match {
      case o: OrderCreated => {
        logger.info(s"OrderCreated:  Processing order: ${o.id} with price: ${o.price}.")

        /*
          Might perform some business logic, based on OrderCreated Event,
           and further persist in db/send message to topic etc.

          For simplicity, I am just printing `OrderCreated: ...`
         */
      }
      case other => logger.info(s"Other Event: $other, we are not concerned about.")
    }

  }
}