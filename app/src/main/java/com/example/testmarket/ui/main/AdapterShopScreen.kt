package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.DelegateShopScreen.bestSalesDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.blockDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.mapDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.progressBestSalesDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.searchDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterShopScreen(
  titleSelectCategory: (titleSelectCategory: String) -> Unit,
  hotBuyId: (id: Int) -> Unit,
  productDetailsId: (id: Int) -> Unit,
  likeBestPressedId: (id: Int) -> Unit,
  filterClick: (Boolean) -> Unit
) :
  AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager
      .addDelegate(blockDelegate(titleSelectCategory, hotBuyId , productDetailsId))
      .addDelegate(searchDelegate())
      .addDelegate(mapDelegate(filterClick))
      .addDelegate(progressBestSalesDelegate())
      .addDelegate(bestSalesDelegate(likeBestPressedId, productDetailsId))
  }
}