package com.example.testmarket.model.main

import com.example.testmarket.model.base.ListItem

data class HotItem(
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
