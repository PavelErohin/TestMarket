package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class ItemSearch(val searchString: String? = null) : ListItem {
  override val itemId = 0
}