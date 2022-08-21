package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemColor(
  val color: String,
  val isSelected: Boolean = false
) : ListItem {
  override val itemId = color.hashCode()
}