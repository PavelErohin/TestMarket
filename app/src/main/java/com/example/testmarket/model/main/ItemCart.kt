package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

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
