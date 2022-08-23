package com.example.testmarket.featureDetails.domain.models

import com.example.testmarket.core.model.ListItem

data class ItemCapacity(
  val capacity: String,
  val isSelected: Boolean = false
) : ListItem {
  override val itemId = capacity.hashCode()
}