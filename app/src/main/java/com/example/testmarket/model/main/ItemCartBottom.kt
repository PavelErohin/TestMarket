package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemCartBottom(
  val delivery: String = "Free",
  val total: Int = 0
) : ListItem {
  override val itemId = 0
}