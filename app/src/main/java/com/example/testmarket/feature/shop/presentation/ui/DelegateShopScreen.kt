package com.example.testmarket.feature.shop.presentation.ui

import android.app.Activity
import android.graphics.Paint
import android.view.View.VISIBLE
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.testmarket.R
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.core.util.MyUtil
import com.example.testmarket.databinding.*
import com.example.testmarket.feature.details.presentation.models.ItemCategory
import com.example.testmarket.feature.shop.models.*
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


object DelegateShopScreen {

  fun blockDelegate(
    titleSelectCategory: (titleSelectCategory: String) -> Unit,
    hotBuyId: (id: Int) -> Unit,
    productDetailsId: (id: Int) -> Unit
  ) =
    adapterDelegateViewBinding<ItemBlockView, ListItem, ItemBlockBinding>(
      { inflater, container -> ItemBlockBinding.inflate(inflater, container, false) }
    ) {
      val adapter = AdapterHorizontal(
        titleSelectCategory, hotBuyId,
        productDetailsId
      )
      binding.blockRecyclerView.adapter = adapter
      bind {
        with(binding) {
          if (item.snapOn)
            if (blockRecyclerView.onFlingListener == null)
              PagerSnapHelper().attachToRecyclerView(blockRecyclerView)
          TVTitle.text = item.title
          bMore.text = item.more
          adapter.items = item.horizontalList
        }
      }
    }

  fun mapDelegate(filterClick: (Boolean) -> Unit) =
    adapterDelegateViewBinding<ItemMap, ListItem, ItemMapBinding>(
      { inflater, container -> ItemMapBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          filter.setOnClickListener { filterClick.invoke(true)/*item.onClick.invoke()*/ }
        }
      }
    }

  fun progressCategoryDelegate() =
    adapterDelegateViewBinding<ProgressItemCategory, ListItem, ItemProgressCategoryBinding>(
      { inflater, container -> ItemProgressCategoryBinding.inflate(inflater, container, false) }
    ) {}

  fun categoryDelegate(titleSelectCategory: (titleSelectCategory: String) -> Unit) =
    adapterDelegateViewBinding<ItemCategory, ListItem, ItemCategoryBinding>(
      { inflater, container -> ItemCategoryBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          viewShape.setOnClickListener { titleSelectCategory.invoke(item.title) }
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

  fun hotSalesDelegate(
    bestBuyId: (id: Int) -> Unit,
    productDetailsId: (id: Int) -> Unit
  ) =
    adapterDelegateViewBinding<ItemHot, ListItem, ItemHotBinding>(
      { inflater, container -> ItemHotBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          buttonBuyNow.setOnClickListener { bestBuyId.invoke(item.id) }
          bestView.setOnClickListener { productDetailsId.invoke(item.id) }
          if (item.isNew) viewShapeNew.visibility = VISIBLE
          if (item.picture.isNotBlank()) {
            Glide.with(IVhot.context).load(item.picture).centerCrop()
              .placeholder(R.drawable.bg_bottom_menu)
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
    likeBestPressedId: (itemBest: Int) -> Unit,
    productDetailsId: (id: Int) -> Unit
  ) =
    adapterDelegateViewBinding<ItemBest, ListItem, ItemBestSellerBinding>(
      { inflater, container -> ItemBestSellerBinding.inflate(inflater, container, false) }
    ) {
      bind {
        with(binding) {
          BLike.setOnClickListener { likeBestPressedId.invoke(item.id) }
          bestView.setOnClickListener { productDetailsId.invoke(item.id) }
          val resources = root.resources
          title = item.title
          if (item.picture.isNotBlank()) {
            Glide.with(root).load(item.picture).override(
              resources.getDimensionPixelOffset(R.dimen.item_best_width),
              resources.getDimensionPixelOffset(R.dimen.item_best_picture_height)
            ).transition(withCrossFade())
              .placeholder(R.drawable.ic_phone_select)
              .error(R.drawable.ic_launcher_foreground)
              .into(imageBest)
          }
          BLike.isSelected = item.isFavorites
          discountPrice = MyUtil().convertToPrice(item.discountPrice)
          price = MyUtil().convertToPrice(item.price)
          TVprice.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
          executePendingBindings()
        }
      }

      onViewRecycled {
        if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
          Glide.with(binding.root).clear(binding.imageBest)
      }
    }
}