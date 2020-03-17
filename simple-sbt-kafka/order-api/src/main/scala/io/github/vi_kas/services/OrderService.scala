package io.github.vi_kas.services

import java.util.UUID

import io.github.vi_kas.Response._
import io.github.vi_kas.data.Model.Order
import io.github.vi_kas.kafka.KafkaProducer
import io.github.vi_kas.kafka.protocol.KafkaProtocol.OrderCreatedEvent
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

trait OrderService {

  def all: Future[ServiceResponse[Seq[Order]]]

  def byId(id: UUID): Future[ServiceResponse[Order]]

  def create(user: Order): Future[ServiceResponse[Order]]

  def delete(id: UUID): Future[ServiceResponse[Boolean]]
}

class OrderServiceImpl(kafkaProducer: KafkaProducer) extends OrderService {

  private val ORDERS_TOPIC = "simple.order"

  private var orders = Map[String, Order]()
  private val logger = LoggerFactory.getLogger(getClass)

  override def all: Future[ServiceResponse[Seq[Order]]] = Future {

    Try(orders.values) match {
      case Failure(exception) => Left(ErrorResponse(exception.getMessage, 0))
      case Success(empty) if empty.isEmpty => Right(empty.toList)
      case Success(nonEmpty) => Right(nonEmpty.toList)
    }
  }

  override def byId(id: UUID): Future[ServiceResponse[Order]] = Future {

    orders.get(id.toString) match {
      case None => Left(ErrorResponse(s"No Order found with ID: $id", 0))
      case Some(order) => Right(order)
    }
  }

  override def create(order: Order): Future[ServiceResponse[Order]] = Future {

    Try(orders.+((order.id.toString, order)))
      .map(updated => orders = updated) match {
      case Failure(exception) => Left(ErrorResponse(exception.getMessage, 0))
      case Success(_) => {
        logger.info(s"Order Creation: Success!")

        kafkaProducer
          .handleEvent(ORDERS_TOPIC, OrderCreatedEvent(order.id.toString, order.title, order.price, order.quantity))

        Right(order)
      }
    }
  }

  override def delete(id: UUID): Future[ServiceResponse[Boolean]] = Future {

    orders.get(id.toString) match {
      case None => Left(ErrorResponse(s"Couldn't find order with id: $id", 0))
      case Some(_) =>
        Try(orders.-(id.toString)) match {
          case Failure(exception) => Left(ErrorResponse(exception.getMessage, 0))
          case Success(_) => Right(true)
        }
    }
  }
}