package io.github.vi_kas.models

import java.util.UUID

case class Order(id: UUID, title: String, quantity: Int, price: Double)