package com.example.testmarket.feature.cart.presentation.ui

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testmarket.R
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.util.MyUtil
import com.example.testmarket.databinding.ItemCartBinding
import com.example.testmarket.databinding.ItemCartBottomBinding
import com.example.testmarket.databinding.ItemCartTopBinding
import com.example.testmarket.databinding.ItemProgressCartBinding
import com.example.testmarket.feature.cart.domain.models.ItemCart
import com.example.testmarket.feature.cart.domain.models.ItemCartBottom
import com.example.testmarket.feature.cart.domain.models.ItemCartTop
import com.example.testmarket.feature.shop.models.ProgressItemCart
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


object DelegateCartScreen {

  fun topCartDelegate(backClick: (Boolean) -> Unit) =
    adapterDelegateViewBinding<ItemCartTop, ListItem, ItemCartTopBinding>(
      { inflater, container -> ItemCartTopBinding.inflate(inflater, container, false) }
    ) {
      binding.close.setOnClickListener { backClick.invoke(true) }
    }

  fun progressItemCartDelegate() =
    adapterDelegateViewBinding<ProgressItemCart, ListItem, ItemProgressCartBinding>(
      { inflater, container -> ItemProgressCartBinding.inflate(inflater, container, false) }
    ) {}

  fun bottomCartDelegate() =
    adapterDelegateViewBinding<ItemCartBottom, ListItem, ItemCartBottomBinding>(
      { inflater, container -> ItemCartBottomBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          total.text = MyUtil().convertToPriceUS(item.total)
          delivery.text = item.delivery
        }
      }
    }

  fun itemCartDelegate(
    plusClickId: (itemCartBottomId: Int) -> Unit,
    minusClickId: (itemCartBottomId: Int) -> Unit,
    deleteClickId: (itemCartBottomId: Int) -> Unit,
    imageClickId: (imageClickId: Int) -> Unit
  ) =
    adapterDelegateViewBinding<ItemCart, ListItem, ItemCartBinding>(
      { inflater, container -> ItemCartBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          val resources = root.resources
          plus.setOnClickListener { plusClickId.invoke(item.id) }
          minus.setOnClickListener { minusClickId.invoke(item.id) }
          delete.setOnClickListener { deleteClickId.invoke(item.id) }
          image.setOnClickListener { imageClickId.invoke(item.id) }
          if (item.images.isNotBlank()) {
            Glide.with(root).load(item.images)
              .override(
                resources.getDimensionPixelOffset(R.dimen.image_cart),
                resources.getDimensionPixelOffset(R.dimen.image_cart)
              ).transform(
                CenterCrop(),
                RoundedCorners(10)
              )
              .placeholder(R.drawable.bg_basket_sum)
              .error(R.drawable.ic_launcher_foreground)
              .into(image)
          }
          sum.text = item.sum.toString()
          title.text = item.title
          price.text = MyUtil().convertToPrice(item.sum * item.price)
        }
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.image)
      }
    }
}