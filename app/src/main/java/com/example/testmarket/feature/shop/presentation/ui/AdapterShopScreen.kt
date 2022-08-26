package com.example.testmarket.feature.shop.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.bestSalesDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.blockDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.mapDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.progressBestSalesDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.searchDelegate
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