package com.example.testmarket.feature.shop.models

import com.example.testmarket.core.model.ListItem

data class ItemHot(
  //val onClick: () -> Unit,
  val id: Int,
  val isNew: Boolean = false,
  val title: String,
  val subtitle: String,
  val picture: String,
  val isBuy: Boolean
) : ListItem
{
  override val itemId = title.hashCode()
}
