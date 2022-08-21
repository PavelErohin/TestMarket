package com.example.testmarket.ui.main

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testmarket.R
import com.example.testmarket.databinding.ItemCartBinding
import com.example.testmarket.databinding.ItemCartBottomBinding
import com.example.testmarket.databinding.ItemCartTopBinding
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCart
import com.example.testmarket.model.main.ItemCartBottom
import com.example.testmarket.model.main.ItemCartTop
import com.example.testmarket.util.MyBaseUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


object DelegateCartScreen {

  fun topCartDelegate() =
    adapterDelegateViewBinding<ItemCartTop, ListItem, ItemCartTopBinding>(
      { inflater, container -> ItemCartTopBinding.inflate(inflater, container, false) }
    ) {}

  fun bottomCartDelegate(/*checkoutClick: (itemCartBottom: ItemCartBottom) -> Unit*/) =
    adapterDelegateViewBinding<ItemCartBottom, ListItem, ItemCartBottomBinding>(
      { inflater, container -> ItemCartBottomBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          /*btCheckout.setOnClickListener { checkoutClick.invoke(item) }*/
          total.text = MyBaseUtil().convertToPriceUS(item.total)
          delivery.text = item.delivery
        }
      }
    }

  fun itemCartDelegate(plusClick: (itemCartBottom: ItemCart) -> Unit,
                       minusClick: (itemCartBottom: ItemCart) -> Unit,
                       deleteClick: (itemCartBottom: ItemCart) -> Unit) =
    adapterDelegateViewBinding<ItemCart, ListItem, ItemCartBinding>(
      { inflater, container -> ItemCartBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          val resources = root.resources
          plus.setOnClickListener { plusClick.invoke(item) }
          minus.setOnClickListener { minusClick.invoke(item) }
          delete.setOnClickListener { deleteClick.invoke(item) }
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
          price.text = MyBaseUtil().convertToPrice(item.price)
        }
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.image)
      }
    }
}