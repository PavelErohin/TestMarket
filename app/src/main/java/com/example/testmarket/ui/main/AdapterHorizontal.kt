package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCategory
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.DelegateMainScreen.categoryDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.hotSalesDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.progressCategoryDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.progressHotDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterHorizontal(selectCategory: (itemCategory: ItemCategory) -> Unit) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager.addDelegate(progressCategoryDelegate())
      .addDelegate(progressHotDelegate())
      .addDelegate(categoryDelegate(selectCategory))
      .addDelegate(hotSalesDelegate())
  }
}