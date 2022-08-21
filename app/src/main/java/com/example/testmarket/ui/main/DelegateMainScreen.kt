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
import com.example.testmarket.util.MyBaseUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


object DelegateMainScreen {

  fun blockDelegate(selectCategory: (itemCategory: ItemCategory) -> Unit) =
    adapterDelegateViewBinding<ItemBlockView, ListItem, ItemBlockBinding>(
      { inflater, container -> ItemBlockBinding.inflate(inflater, container, false) }
    ) {
      val adapter = AdapterHorizontal(selectCategory)
      binding.blockRecyclerView.adapter = adapter
      bind {
        with(binding) {
          if (item.snapOn)
            if (blockRecyclerView.getOnFlingListener() == null)
              PagerSnapHelper().attachToRecyclerView(blockRecyclerView)
          TVTitle.text = item.title
          bMore.text = item.more
          adapter.items = item.horizontalList
        }
      }
    }

  fun mapDelegate() =
    adapterDelegateViewBinding<ItemMap, ListItem, ItemMapBinding>(
      { inflater, container -> ItemMapBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          filter.setOnClickListener { item.onClick.invoke() }
        }
      }
    }

  fun progressCategoryDelegate() =
    adapterDelegateViewBinding<ProgressItemCategory, ListItem, ItemProgressCategoryBinding>(
      { inflater, container -> ItemProgressCategoryBinding.inflate(inflater, container, false) }
    ) {}

  fun categoryDelegate(selectCategory: (itemCategory: ItemCategory) -> Unit) =
    adapterDelegateViewBinding<ItemCategory, ListItem, ItemCategoryBinding>(
      { inflater, container -> ItemCategoryBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          viewShape.setOnClickListener { selectCategory.invoke(item) }
          viewShape.setImageResource(item.icon)
          viewShape.isSelected = item.selected
          title = item.title
          executePendingBindings()
        }
      }
    }

  fun searchDelegate() =
    adapterDelegateViewBinding<ItemSearch, ListItem, ItemSearchBinding>(
      { inflater, container -> ItemSearchBinding.inflate(inflater, container, false) }
    ) {}

  fun progressHotDelegate() =
    adapterDelegateViewBinding<ProgressItemHot, ListItem, ItemProgressHotBinding>(
      { inflater, container -> ItemProgressHotBinding.inflate(inflater, container, false) }
    ) {}

  fun hotSalesDelegate() =
    adapterDelegateViewBinding<ItemHot, ListItem, ItemHotBinding>(
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
    adapterDelegateViewBinding<ProgressItemBest, ListItem, ItemProgressBestSellerBinding>(
      { inflater, container -> ItemProgressBestSellerBinding.inflate(inflater, container, false) }
    ) {}

  fun bestSalesDelegate(
    selectBest: (itemBest: ItemBest) -> Unit,
    toDetailsClick: (id: Int) -> Unit
  ) =
    adapterDelegateViewBinding<ItemBest, ListItem, ItemBestSellerBinding>(
      { inflater, container -> ItemBestSellerBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          BLike.setOnClickListener { selectBest.invoke(item) }
          IVbest.setOnClickListener { toDetailsClick.invoke(item.id) }
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
          BLike.isSelected = item.isFavorites
          discountPrice = MyBaseUtil().convertToPrice(item.discountPrice)
          price = MyBaseUtil().convertToPrice(item.price)
          TVprice.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
          executePendingBindings()
        }
      }

      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.IVbest)
      }
    }
}