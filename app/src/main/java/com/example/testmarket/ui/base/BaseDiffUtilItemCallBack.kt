package com.example.testmarket.ui.base

import androidx.recyclerview.widget.DiffUtil
import com.example.testmarket.model.base.ListItem

open class BaseDiffUtilItemCallBack : DiffUtil.ItemCallback<ListItem>() {
  companion object DiffCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
      return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
      return oldItem.itemId == newItem.itemId
    }
  }

  override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
    oldItem.itemId == newItem.itemId

  override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.equals(newItem)
  }

  override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
    if (oldItem != newItem) return newItem
    return super.getChangePayload(oldItem, newItem)
  }
}