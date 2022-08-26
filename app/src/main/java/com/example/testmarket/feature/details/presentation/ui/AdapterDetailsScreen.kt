package com.example.testmarket.feature.details.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.example.testmarket.feature.details.presentation.models.ItemCapacity
import com.example.testmarket.feature.details.presentation.models.ItemColor
import com.example.testmarket.feature.details.presentation.ui.DelegateDetailsScreen.colorDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ColorAdapter(selectColor: (itemColor: ItemColor) -> Unit) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(colorDelegate(selectColor))
  }
}

class CapacityAdapter(selectCapacity: (itemCapacity: ItemCapacity) -> Unit) :
  AsyncListDifferDelegationAdapter<ListItem>(
    BaseDiffUtilItemCallBack()
  ) {
  init {
    delegatesManager
      .addDelegate(DelegateDetailsScreen.capacityDelegate(selectCapacity))
  }
}