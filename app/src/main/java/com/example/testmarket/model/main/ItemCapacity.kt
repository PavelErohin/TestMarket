package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemCapacity(
  val capacity: String,
  val isSelected: Boolean = false
) : ListItem {
  override val itemId = capacity.hashCode()
}