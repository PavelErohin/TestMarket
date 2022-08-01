package com.example.testmarket.ui.main

import com.example.testmarket.model.base.ListItem
import com.example.testmarket.ui.base.BaseDiffUtilItemCallBack
import com.example.testmarket.ui.main.MainScreenDelegate.bestSalesDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.blockDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.mapDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.progressBestSalesDelegate
import com.example.testmarket.ui.main.MainScreenDelegate.searchDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallBack()) {

  //Diff_calback?? todo
  init {
    delegatesManager
      .addDelegate(blockDelegate())
      .addDelegate(searchDelegate())
      .addDelegate(mapDelegate())
      .addDelegate(progressBestSalesDelegate())
      .addDelegate(bestSalesDelegate())
  }
}