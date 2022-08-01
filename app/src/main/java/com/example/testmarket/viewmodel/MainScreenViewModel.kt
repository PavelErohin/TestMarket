package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.R
import com.example.testmarket.core.network.Network
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
  private val api = Network.createApi()

  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data

  private val _filter = MutableLiveData<Unit>()
  val filter: LiveData<Unit> = _filter

  init {
    viewModelScope.launch(Dispatchers.IO) {
      _data.postValue(getLoaders())
      _data.postValue(getMainItems())
    }
  }

  private suspend fun getMainItems(): List<ListItem> {
    delay(1500)
    val listItem: MutableList<ListItem> = mutableListOf(
      MapItem{_filter.postValue(Unit)},
      BlockView(
        title = "Select Category",
        more = "view all",
        categoryList = loadCategory()
      ),
      SearchItem,
      BlockView(
        title = "Hot sales",
        categoryList = loadListHotItem(),
        snapOn = true
      ),
      BlockView(
        title = "Best Seller",
        categoryList = null
      )
    )
    listItem.addAll(loadListBestItem())
    listItem.addAll(loadListBestItem())
    listItem.addAll(loadListBestItem())
    listItem.addAll(loadListBestItem())
    listItem.addAll(loadListBestItem())
    listItem.addAll(loadListBestItem())
    return listItem
  }

  private fun getLoaders(): List<ListItem> {
    return listOf(
      MapItem{},
      BlockView(
        title = "Select Category",
        more = "view all",
        categoryList = IntRange(1, 5).map { ProgressCategoryItem }
      ),
      SearchItem,
      BlockView(
        title = "Hot sales",
        categoryList = IntRange(1, 1).map { ProgressHotItem }
      ),
      BlockView(
        title = "Best Seller",
        categoryList = null
      ),
      ProgressBestSellerItem,
      ProgressBestSellerItem,
      ProgressBestSellerItem,
      ProgressBestSellerItem
    )
  }

  private suspend fun loadListHotItem(): List<ListItem> {
    val hotListResponse = api.getMainPageList()
    return hotListResponse.hotList.map {
      HotItem(
        id = it.id,
        isNew = it.isNew,
        title = it.title,
        subtitle = it.subtitle,
        picture = it.picture,
        isBuy = it.isBuy
      )
    }
  }

  private suspend fun loadListBestItem(): List<ListItem> {
    val bestListResponse = api.getMainPageList()
    return bestListResponse.bestList.map {
      BestItem(
        id = it.id,
        title = it.title,
        isFavorites = it.isFavorites,
        discountPrice = it.priceWithoutDiscount,
        price = it.discountPrice,
        picture = it.picture
      )
    }
  }

  private fun loadCategory(): List<CategoryItem> {
    return listOf(
      CategoryItem(
        title = "Phones",
        icon = R.drawable.sel_ic_phones,
        selected = true,
        onClick = { onCategoryPressed("Phones") }),
      CategoryItem(
        title = "Computer",
        icon = R.drawable.sel_ic_computer,
        selected = false,
        onClick = { onCategoryPressed("Computer") }),
      CategoryItem(
        title = "Health",
        icon = R.drawable.sel_ic_health,
        selected = false,
        onClick = { onCategoryPressed("Health") }),
      CategoryItem(
        title = "Books",
        icon = R.drawable.sel_ic_books,
        selected = false,
        onClick = { onCategoryPressed("Books") }),
      CategoryItem(
        title = "5 category",
        icon = R.drawable.sel_ic_phones,
        selected = false,
        onClick = { onCategoryPressed("5 category") })
    )
  }

  private fun onCategoryPressed(title: String) {
    _data.value = _data.value?.map {
      if (it is BlockView) {
        val a = it.categoryList?.map { item ->
          val category = item as? CategoryItem
          if (category != null)
            if (category.title == title) category.copy(selected = true) else category.copy(selected = false)
          else item
        }
        it.copy(categoryList = a)
      } else {
        it
      }
    }
  }

  private fun filtered(): FilterOptions
  {

    return FilterOptions(null,null,null)
  }
}