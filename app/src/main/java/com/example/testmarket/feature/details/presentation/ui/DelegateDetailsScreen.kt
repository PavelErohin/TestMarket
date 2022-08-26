package com.example.testmarket.feature.details.presentation.ui

import android.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.databinding.ItemCapacityBinding
import com.example.testmarket.databinding.ItemColorBinding
import com.example.testmarket.feature.details.presentation.models.ItemCapacity
import com.example.testmarket.feature.details.presentation.models.ItemColor
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object DelegateDetailsScreen {

  fun colorDelegate(selectColor: (itemColor: ItemColor) -> Unit) =
    adapterDelegateViewBinding<ItemColor, ListItem, ItemColorBinding>(
      { inflater, container -> ItemColorBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          ibColor.setOnClickListener { selectColor.invoke(item) }
          color = item.color.toColorInt()
          chek.isVisible = item.isSelected
        }
      }
    }

  fun capacityDelegate(selectCapacity: (itemCapacity: ItemCapacity) -> Unit) =
    adapterDelegateViewBinding<ItemCapacity, ListItem, ItemCapacityBinding>(
      { inflater, container -> ItemCapacityBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          bCapacity.setOnClickListener { selectCapacity.invoke(item) }
          tvCapacity.text = item.capacity
          bCapacity.isSelected = item.isSelected
          if (item.isSelected) {
            tvCapacity.setTextColor(Color.WHITE)
          } else tvCapacity.setTextColor(Color.parseColor("#8D8D8D"))

        }
      }
    }
}