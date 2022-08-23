package com.example.testmarket.featureCart.domain.models

data class ScreenDataCart(
  val basket: List<ItemCart>,
  val delivery: String,
  val id: Int,
  val total: Int
)
