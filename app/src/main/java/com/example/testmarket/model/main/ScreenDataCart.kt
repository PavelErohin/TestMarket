package com.example.testmarket.model.main

data class ScreenDataCart(
  val basket: List<ItemCart>,
  val delivery: String,
  val id: Int,
  val total: Int
)
