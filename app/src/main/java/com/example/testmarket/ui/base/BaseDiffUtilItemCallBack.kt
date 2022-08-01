package com.example.testmarket.ui.base

import androidx.recyclerview.widget.DiffUtil
import com.example.testmarket.model.base.ListItem

open class  BaseDiffUtilItemCallBack : DiffUtil.ItemCallback<ListItem>() {
  override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
    oldItem.itemId == newItem.itemId

  override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.equals(newItem)
  }
}