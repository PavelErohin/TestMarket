package com.example.testmarket.featureCart.domain.models

import com.example.testmarket.core.model.ListItem

data class ItemCartBottom(
  val delivery: String = "Free",
  val total: Int = 0
) : ListItem {
  override val itemId = 0
}