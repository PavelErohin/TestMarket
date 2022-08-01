package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class CategoryItem(
  val title: String,
  val icon: Int,
  val selected: Boolean = false,
  val onClick: () -> Unit
) : ListItem
{
  override val itemId = title.hashCode()
}