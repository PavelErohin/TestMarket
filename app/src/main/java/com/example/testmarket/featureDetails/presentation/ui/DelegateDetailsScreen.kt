package com.example.testmarket.featureDetails.presentation.ui

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.graphics.toColorInt
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testmarket.R
import com.example.testmarket.databinding.ItemCapacityBinding
import com.example.testmarket.databinding.ItemColorBinding
import com.example.testmarket.databinding.ItemDetailsGalleryBinding
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.featureDetails.domain.models.ItemCapacity
import com.example.testmarket.featureDetails.domain.models.ItemColor
import com.example.testmarket.featureDetails.domain.models.ItemGallery
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object DelegateDetailsScreen {
  fun detailsGalleryDelegate() =
    adapterDelegateViewBinding<ItemGallery, ListItem, ItemDetailsGalleryBinding>(
      { inflater, container -> ItemDetailsGalleryBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          val resources = root.resources
          if (item.images.isNotBlank()) {
            Glide.with(root).load(item.images).override(
              resources.getDimensionPixelOffset(R.dimen.item_details_width),
              resources.getDimensionPixelOffset(R.dimen.item_details_height)
            )
              .transform(
                CenterCrop(),
                RoundedCorners(resources.getDimensionPixelOffset(R.dimen.item_details_corner_radius))
              )
              .transition(DrawableTransitionOptions.withCrossFade())
              .placeholder(R.drawable.ic_launcher_foreground)
              .error(R.drawable.ic_launcher_foreground)
              .into(IVGallery)
          }
        }
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.IVGallery)
      }
    }

  fun colorDelegate(selectColor: (itemColor: ItemColor) -> Unit) =
    adapterDelegateViewBinding<ItemColor, ListItem, ItemColorBinding>(
      { inflater, container -> ItemColorBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          ibColor.setOnClickListener { selectColor.invoke(item) }
          color = item.color.toColorInt()
          if (item.isSelected)
            chek.visibility = View.VISIBLE else chek.visibility = View.GONE
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