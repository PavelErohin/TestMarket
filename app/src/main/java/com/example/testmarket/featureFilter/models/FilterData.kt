package com.example.testmarket.featureFilter.models

import com.example.testmarket.core.model.ListItem

data class FilterData(
  val brand: String?,
  val price: String?,
  val size: String?
) : ListItem {
  override val itemId get() = hashCode()
}
