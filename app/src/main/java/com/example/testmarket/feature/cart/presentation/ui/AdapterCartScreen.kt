package com.example.testmarket.feature.cart.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterCartScreen(
  backClick: (Boolean) -> Unit,
  plusClickId: (itemCartBottomId: Int) -> Unit,
  minusClickId: (itemCartBottomId: Int) -> Unit,
  deleteClickId: (itemCartBottomId: Int) -> Unit,
  imageClickId: (imageClickId: Int) -> Unit

) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(DelegateCartScreen.topCartDelegate(backClick))
      .addDelegate(DelegateCartScreen.progressItemCartDelegate())
      .addDelegate(
        DelegateCartScreen.itemCartDelegate(plusClickId, minusClickId, deleteClickId, imageClickId)
      )
      .addDelegate(DelegateCartScreen.bottomCartDelegate())
  }
}