package com.example.testmarket.featureCart.domain.models

import com.example.testmarket.core.model.ListItem

data class ItemCart(
  val id: Int,
  val images: String,
  val price: Int,
  val title: String,
  val sum:Int = 1
) : ListItem
{
  override val itemId = title.hashCode()
}
