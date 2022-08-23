package com.example.testmarket.featureDetails.domain.models

import com.example.testmarket.core.model.ListItem

data class ItemCategory(
  val title: String,
  val icon: Int,
  val selected: Boolean = false
) : ListItem
{
  override val itemId = title.hashCode()
}