package com.example.testmarket.feature.details.presentation.models

import com.example.testmarket.core.model.ListItem

data class ItemGallery(
  val images: String
) : ListItem {
  override val itemId = 0
}