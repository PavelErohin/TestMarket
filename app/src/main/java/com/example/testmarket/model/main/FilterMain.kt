package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class FilterMain(
  val brand: String?,
  val price: String?,
  val size: String?
) : ListItem {
  override val itemId get() = hashCode()
}
