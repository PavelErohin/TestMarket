package com.example.testmarket.featureFilter.presentation

import com.example.testmarket.databinding.DialogFilterBinding
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.featureFilter.models.FilterData
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filterDelegate() =
  adapterDelegateViewBinding<FilterData, ListItem, DialogFilterBinding>({ layoutInflater, parent ->
    DialogFilterBinding.inflate(
      layoutInflater,
      parent,
      false
    )
  }) {

  }