package com.example.testmarket.ui.main

import com.example.testmarket.databinding.DialogFilterBinding
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.FilterMain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filterDelegate() =
  adapterDelegateViewBinding<FilterMain, ListItem, DialogFilterBinding>({ layoutInflater, parent ->
    DialogFilterBinding.inflate(
      layoutInflater,
      parent,
      false
    )
  }) {

  }