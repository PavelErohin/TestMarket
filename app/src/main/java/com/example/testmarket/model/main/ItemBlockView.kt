package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemBlockView(
  val title: String,
  val more: String = "see more",
  val horizontalList: List<ListItem>?,
  val snapOn: Boolean = false
) : ListItem {
  override val itemId = title.hashCode()
}
