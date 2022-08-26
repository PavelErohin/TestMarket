package com.example.testmarket.feature.shop.presentation.ui

import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.ui.BaseDiffUtilItemCallBack
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.categoryDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.hotSalesDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.progressCategoryDelegate
import com.example.testmarket.feature.shop.presentation.ui.DelegateShopScreen.progressHotDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdapterHorizontal(
  titleSelectCategory: (titleSelectCategory: String) -> Unit,
  hotBuyId: (id: Int) -> Unit,
  productDetailsId: (id: Int) -> Unit
) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {
  init {
    delegatesManager.addDelegate(progressCategoryDelegate())
      .addDelegate(progressHotDelegate())
      .addDelegate(categoryDelegate(titleSelectCategory))
      .addDelegate(hotSalesDelegate(hotBuyId, productDetailsId))
  }
}