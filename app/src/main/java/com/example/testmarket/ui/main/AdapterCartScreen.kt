package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterCartScreen(
  plusClickId: (itemCartBottomId: Int) -> Unit,
  minusClickId: (itemCartBottomId: Int) -> Unit,
  deleteClickId: (itemCartBottomId: Int) -> Unit,
  imageClickId: (imageClickId: Int) -> Unit,
  backClick: (Boolean) -> Unit/*,
  checkoutClick: (itemCartBottom: ItemCartBottom) -> Unit*/
) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(DelegateCartScreen.topCartDelegate(backClick))
      .addDelegate(DelegateCartScreen.progressItemCartDelegate())
      .addDelegate(
        DelegateCartScreen.itemCartDelegate(plusClickId, minusClickId, deleteClickId, imageClickId)
      )
      .addDelegate(DelegateCartScreen.bottomCartDelegate(/*checkoutClick*/))
  }
}