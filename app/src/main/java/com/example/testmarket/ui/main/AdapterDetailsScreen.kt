package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCapacity
import com.example.testmarket.model.main.ItemColor
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.DelegateDetailsScreen.colorDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ColorAdapter(selectColor: (itemColor: ItemColor) -> Unit) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(colorDelegate(selectColor))
  }
}

class GalleryAdapter : AsyncListDifferDelegationAdapter<ListItem>(
  BaseDiffUtilItemCallBack()
) {
  init {
    delegatesManager
      .addDelegate(DelegateDetailsScreen.detailsGalleryDelegate())
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