package com.example.testmarket.featureCart.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterCartScreen(
  plusClickId: (itemCartBottomId: Int) -> Unit,
  minusClickId: (itemCartBottomId: Int) -> Unit,
  deleteClickId: (itemCartBottomId: Int) -> Unit,
  imageClickId: (imageClickId: Int) -> Unit,
  backClick: (Boolean) -> Unit
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