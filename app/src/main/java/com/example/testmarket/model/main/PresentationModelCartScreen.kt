package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class PresentationModelCartScreen(
  val itemCartTop: ItemCartTop = ItemCartTop,
  val bestItems: List<ListItem> = listOf(
    ProgressItemCart,
    ProgressItemCart
  ),
  val itemCartBottom: ItemCartBottom = ItemCartBottom(),
)
