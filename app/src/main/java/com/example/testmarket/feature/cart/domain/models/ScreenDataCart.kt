package com.example.testmarket.feature.cart.domain.models

data class ScreenDataCart(
  val basket: List<ItemCart>,
  val delivery: String,
  val id: Int,
  val total: Int
)
