package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemGallery(
  val images: String
) : ListItem {
  override val itemId = 0
}