package com.example.testmarket.ui.main

import android.app.Activity
import android.graphics.Paint
import android.view.View.VISIBLE
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.testmarket.R
import com.example.testmarket.databinding.*
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.*
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.NumberFormat
import java.util.*


object MainScreenDelegate {

  fun blockDelegate() =
    adapterDelegateViewBinding<BlockView, ListItem, ItemBlockBinding>(
      { inflater, container -> ItemBlockBinding.inflate(inflater, container, false) }
    ) {
      val adapter = HorizontalAdapter()
      binding.blockRecyclerView.adapter = adapter
      bind {
        with(binding) {
          if (item.snapOn == true)
            if (blockRecyclerView.getOnFlingListener() == null)
              PagerSnapHelper().attachToRecyclerView(blockRecyclerView)
          TVTitle.text = item.title
          bMore.text = item.more
          adapter.items = item.categoryList
        }
      }
    }

  fun mapDelegate() =
    adapterDelegateViewBinding<MapItem, ListItem, ItemMapBinding>(
      { inflater, container -> ItemMapBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          filter.setOnClickListener { filterAlertDialog() }
        }
      }
    }

  fun progressCategoryDelegate() =
    adapterDelegateViewBinding<ProgressCategoryItem, ListItem, ItemProgressCategoryBinding>(
      { inflater, container -> ItemProgressCategoryBinding.inflate(inflater, container, false) }
    ) {}

  fun categoryDelegate() =
    adapterDelegateViewBinding<CategoryItem, ListItem, ItemCategoryBinding>(
      { inflater, container -> ItemCategoryBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          viewShape.setOnClickListener {
            item.onClick()
          } //todo
          viewShape.setImageResource(item.icon)
          viewShape.isSelected = item.selected
          title = item.title
          executePendingBindings()
        }
      }

    }

  fun searchDelegate() =
    adapterDelegateViewBinding<SearchItem, ListItem, ItemSearchBinding>(
      { inflater, container -> ItemSearchBinding.inflate(inflater, container, false) }
    ) {}

  fun progressHotDelegate() =
    adapterDelegateViewBinding<ProgressHotItem, ListItem, ItemProgressHotBinding>(
      { inflater, container -> ItemProgressHotBinding.inflate(inflater, container, false) }
    ) {}

  fun hotSalesDelegate() =
    adapterDelegateViewBinding<HotItem, ListItem, ItemHotBinding>(
      { inflater, container -> ItemHotBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          if (item.isNew == true) viewShapeNew.visibility = VISIBLE
          if (item.picture.isNotBlank()) {
            Glide.with(IVhot.context).load(item.picture).centerCrop()
              .placeholder(R.drawable.bg_rectangle_dark_blue)
              .error(R.drawable.ic_launcher_foreground)
              .into(IVhot)
          }
          title = item.title
          subtitle = item.subtitle
          executePendingBindings()
        }
      }
      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.IVhot)
      }
    }

  fun progressBestSalesDelegate() =
    adapterDelegateViewBinding<ProgressBestSellerItem, ListItem, ItemProgressBestSellerBinding>(
      { inflater, container -> ItemProgressBestSellerBinding.inflate(inflater, container, false) }
    ) {}

  fun bestSalesDelegate() =
    adapterDelegateViewBinding<BestItem, ListItem, ItemBestSellerBinding>(
      { inflater, container -> ItemBestSellerBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          val resources = root.resources
          title = item.title
          if (item.picture.isNotBlank()) {
            Glide.with(root).load(item.picture).override(
              resources.getDimensionPixelOffset(R.dimen.item_best_width),
              resources.getDimensionPixelOffset(R.dimen.item_best_picture_height)
            ).transition(withCrossFade())
              .placeholder(R.drawable.ic_phone_select)
              .error(R.drawable.ic_launcher_foreground)
              .into(IVbest)
          }
          discountPrice = "$" + NumberFormat.getNumberInstance(Locale.US).format(item.discountPrice)
          price = "$" + NumberFormat.getNumberInstance(Locale.US).format(item.price)
          TVprice.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
          executePendingBindings()
        }
      }

      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.IVbest)
      }
    }

  private fun filterAlertDialog() {

  }
}