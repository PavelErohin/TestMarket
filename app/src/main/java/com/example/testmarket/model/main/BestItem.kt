package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class BestItem(
  val id: Int,
  val isFavorites: Boolean,
  val title: String,
  val discountPrice: Int,
  val price: Int,
  val picture: String
) : ListItem {
  override val itemId = title.hashCode()
}