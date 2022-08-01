package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.MainScreenDelegate.categoryDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.hotSalesDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.progressCategoryDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.progressHotDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HorizontalAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager.addDelegate(progressCategoryDelegate())
      .addDelegate(progressHotDelegate())
      .addDelegate(categoryDelegate())
      .addDelegate(hotSalesDelegate())
  }


}