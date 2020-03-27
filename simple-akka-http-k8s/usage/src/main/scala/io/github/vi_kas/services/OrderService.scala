package io.github.vi_kas.services

import java.util.UUID

import io.github.vi_kas.Response._
import io.github.vi_kas.data.Model.Order

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

trait OrderService {

  def all: Future[ServiceResponse[Seq[Order]]]

  def byId(id: UUID): Future[ServiceResponse[Order]]

  def create(user: Order): Future[ServiceResponse[Order]]

  def delete(id: UUID): Future[ServiceResponse[Boolean]]
}

class OrderServiceImpl() extends OrderService {

  private var orders = Map[String, Order]()

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
      case Success(_) => Right(order)
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