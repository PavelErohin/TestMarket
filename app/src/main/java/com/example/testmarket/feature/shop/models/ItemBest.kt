package com.example.testmarket.feature.shop.models

import com.example.testmarket.core.model.ListItem

data class ItemBest(
  val id: Int,
  val isFavorites: Boolean,
  val title: String,
  val discountPrice: Int,
  val price: Int,
  val picture: String
) : ListItem {
  override val itemId = title.hashCode()
}