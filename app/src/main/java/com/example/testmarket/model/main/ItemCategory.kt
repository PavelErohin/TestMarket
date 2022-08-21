package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemCategory(
  val title: String,
  val icon: Int,
  val selected: Boolean = false
) : ListItem
{
  override val itemId = title.hashCode()
}