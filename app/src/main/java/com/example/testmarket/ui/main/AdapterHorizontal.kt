package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.DelegateShopScreen.categoryDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.hotSalesDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.progressCategoryDelegate
import com.example.testmarket.ui.main.DelegateShopScreen.progressHotDelegate
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