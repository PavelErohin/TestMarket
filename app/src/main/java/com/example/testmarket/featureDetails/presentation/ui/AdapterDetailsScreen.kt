package com.example.testmarket.featureDetails.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.featureDetails.domain.models.ItemCapacity
import com.example.testmarket.featureDetails.domain.models.ItemColor
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.example.testmarket.featureDetails.presentation.ui.DelegateDetailsScreen.colorDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ColorAdapter(selectColor: (itemColor: ItemColor) -> Unit) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(colorDelegate(selectColor))
  }
}

/*class GalleryAdapter : AsyncListDifferDelegationAdapter<ListItem>(
  BaseDiffUtilItemCallBack()
) {
  init {
    delegatesManager
      .addDelegate(DelegateDetailsScreen.detailsGalleryDelegate())
  }
}*/

class CapacityAdapter(selectCapacity: (itemCapacity: ItemCapacity) -> Unit) :
  AsyncListDifferDelegationAdapter<ListItem>(
    BaseDiffUtilItemCallBack()
  ) {
  init {
    delegatesManager
      .addDelegate(DelegateDetailsScreen.capacityDelegate(selectCapacity))
  }
}