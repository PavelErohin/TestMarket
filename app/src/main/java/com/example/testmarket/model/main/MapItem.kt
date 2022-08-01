package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class MapItem(val onClick: () -> Unit) : ListItem {
  override val itemId = 0
}