package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCart
import com.example.testmarket.model.main.ItemCartBottom
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterCartScreen(
  plusClick: (itemCartBottom: ItemCart) -> Unit,
  minusClick: (itemCartBottom: ItemCart) -> Unit,
  deleteClick: (itemCartBottom: ItemCart) -> Unit/*,
  checkoutClick: (itemCartBottom: ItemCartBottom) -> Unit*/
) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(DelegateCartScreen.topCartDelegate())
      .addDelegate(
        DelegateCartScreen.itemCartDelegate(plusClick, minusClick, deleteClick))
          .addDelegate(DelegateCartScreen.bottomCartDelegate(/*checkoutClick*/))
  }
}