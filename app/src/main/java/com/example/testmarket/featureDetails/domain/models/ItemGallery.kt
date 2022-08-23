package com.example.testmarket.featureDetails.domain.models

import com.example.testmarket.core.model.ListItem

data class ItemGallery(
  val images: String
) : ListItem {
  override val itemId = 0
}