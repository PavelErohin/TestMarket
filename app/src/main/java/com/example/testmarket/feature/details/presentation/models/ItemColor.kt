package com.example.testmarket.feature.details.presentation.models

import com.example.testmarket.core.model.ListItem

data class ItemColor(
  val color: String,
  val isSelected: Boolean = false
) : ListItem {
  override val itemId = color.hashCode()
}