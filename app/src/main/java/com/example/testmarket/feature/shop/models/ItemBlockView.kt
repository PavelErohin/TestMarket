package com.example.testmarket.feature.shop.models

import com.example.testmarket.core.model.ListItem

data class ItemBlockView(
  val title: String,
  val more: String = "see more",
  val horizontalList: List<ListItem>?,
  val snapOn: Boolean = false
) : ListItem {
  override val itemId = title.hashCode()
}
