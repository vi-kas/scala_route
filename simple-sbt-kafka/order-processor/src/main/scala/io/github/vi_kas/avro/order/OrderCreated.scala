/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package io.github.vi_kas.avro.order

import scala.annotation.switch

final case class OrderCreated(var id: String, var title: String, var price: Double, var quantity: Int) extends org.apache.avro.specific.SpecificRecordBase {
  def this() = this("", "", 0.0, 0)
  def get(field$: Int): AnyRef = {
    (field$: @switch) match {
      case 0 => {
        id
      }.asInstanceOf[AnyRef]
      case 1 => {
        title
      }.asInstanceOf[AnyRef]
      case 2 => {
        price
      }.asInstanceOf[AnyRef]
      case 3 => {
        quantity
      }.asInstanceOf[AnyRef]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
  }
  def put(field$: Int, value: Any): Unit = {
    (field$: @switch) match {
      case 0 => this.id = {
        value.toString
      }.asInstanceOf[String]
      case 1 => this.title = {
        value.toString
      }.asInstanceOf[String]
      case 2 => this.price = {
        value
      }.asInstanceOf[Double]
      case 3 => this.quantity = {
        value
      }.asInstanceOf[Int]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
    ()
  }
  def getSchema: org.apache.avro.Schema = OrderCreated.SCHEMA$
}

object OrderCreated {
  val SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderCreated\",\"namespace\":\"io.github.vi_kas.avro.order\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"title\",\"type\":\"string\"},{\"name\":\"price\",\"type\":\"double\"},{\"name\":\"quantity\",\"type\":\"int\"}]}")
}