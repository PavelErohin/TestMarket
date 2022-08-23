package com.example.testmarket.featureShop.models

import com.example.testmarket.core.model.ListItem

data class ItemSearch(val searchString: String? = null) : ListItem {
  override val itemId = 0
}