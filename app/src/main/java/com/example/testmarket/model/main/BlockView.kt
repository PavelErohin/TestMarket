package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class BlockView(
  val title: String,
  val more: String = "see more",
  val categoryList: List<ListItem>?,
  val snapOn: Boolean = false
) : ListItem {
  override val itemId = title.hashCode()
}
