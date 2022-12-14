package com.example.testmarket.feature.shop.models

import com.example.testmarket.core.model.ListItem

data class PresentationModelShopScreen(

  val itemMap: ItemMap = ItemMap,
  val itemBlockCategory: ItemBlockView = ItemBlockView(
    title = "Select Category",
    more = "view all",
    horizontalList = IntRange(1, 5).map { ProgressItemCategory }
  ),
  val itemSearch: ItemSearch = ItemSearch(),
  val itemBlockSearch: ItemBlockView = ItemBlockView(
    title = "Hot sales",
    horizontalList = listOf(ProgressItemHot)
  ),
  val itemBlockBest: ItemBlockView = ItemBlockView(
    title = "Best Seller",
    horizontalList = null
  ),
  val bestItems: List<ListItem> = listOf(
    ProgressItemBest,
    ProgressItemBest,
    ProgressItemBest,
    ProgressItemBest
  )
)
