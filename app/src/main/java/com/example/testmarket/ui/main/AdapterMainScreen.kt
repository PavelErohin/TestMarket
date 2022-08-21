package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemBest
import com.example.testmarket.model.main.ItemCategory
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.DelegateMainScreen.bestSalesDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.blockDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.mapDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.progressBestSalesDelegate
import com.example.testmarket.ui.main.DelegateMainScreen.searchDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterMainScreen(
  selectCategory: (itemCategory: ItemCategory) -> Unit,
  selectBest: (itemBest: ItemBest) -> Unit,
  toDetailsClick: (id: Int) -> Unit
) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(blockDelegate(selectCategory))
      .addDelegate(searchDelegate())
      .addDelegate(mapDelegate())
      .addDelegate(progressBestSalesDelegate())
      .addDelegate(bestSalesDelegate(selectBest, toDetailsClick))
  }
}