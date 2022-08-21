package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.R
import com.example.testmarket.core.network.Network
import com.example.testmarket.core.network.model.MainPageResponse
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

  private val api = Network.createApi() //todo di

  //private val _dataBest = MutableLiveData<List<BestItem>>()
  //val dataBest: LiveData<List<BestItem>> = _dataBest
  //todo mediatorlivedata отдельные лайв даты для hot, best, cat?
  //todo добавления в список best того же при листании вниз (типа пагинация)
  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data
  private val _filter = MutableLiveData<Unit>()
  val filter: LiveData<Unit> = _filter
  private var presentationModel = PresentationModelMainScreen()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val mainScreenData = api.getMainListData()
      _data.postValue(presentationModel.mapToDelegateList())
      delay(1000)
      presentationModel = presentationModel.copy(
        itemMap = ItemMap { _filter.value = Unit },
        itemBlockCategory = ItemBlockView(
          title = "Select Category",
          more = "view all",
          horizontalList = loadCategory()
        ),
        itemSearch = ItemSearch(),
        itemBlockSearch = ItemBlockView(
          title = "Hot sales",
          horizontalList = loadListHotItem(mainScreenData),
          snapOn = true
        ),
        itemBlockBest = ItemBlockView(
          title = "Best Seller",
          horizontalList = null
        )
      )
      presentationModel = presentationModel.copy(bestItems = loadListBestItem(mainScreenData))
      _data.postValue(presentationModel.mapToDelegateList())
    }
  }

  private fun PresentationModelMainScreen.mapToDelegateList(): List<ListItem> {
    return listOf(
      itemMap,
      itemBlockCategory,
      itemSearch,
      itemBlockSearch,
      itemBlockBest
    ) + bestItems
  }

  private fun loadListHotItem(mainScreenData: MainPageResponse): List<ListItem> =
    mainScreenData.hotList.map {
      ItemHot(
        id = it.id,
        isNew = it.isNew,
        title = it.title,
        subtitle = it.subtitle,
        picture = it.picture,
        isBuy = it.isBuy
      )
    }

  private fun loadListBestItem(mainScreenData: MainPageResponse): List<ListItem> =
    mainScreenData.bestList.map {
      ItemBest(
        {},
        id = it.id,
        title = it.title,
        isFavorites = it.isFavorites,
        discountPrice = it.priceWithoutDiscount,
        price = it.discountPrice,
        picture = it.picture
      )
    }

  private fun loadCategory(): List<ItemCategory> {
    return listOf(
      ItemCategory(
        title = "Phones",
        icon = R.drawable.sel_ic_phones,
        selected = true
      ),
      ItemCategory(
        title = "Computer",
        icon = R.drawable.sel_ic_computer,
        selected = false
      ),
      ItemCategory(
        title = "Health",
        icon = R.drawable.sel_ic_health,
        selected = false
      ),
      ItemCategory(
        title = "Books",
        icon = R.drawable.sel_ic_books,
        selected = false
      ),
      ItemCategory(
        title = "5 category",
        icon = R.drawable.sel_ic_phones,
        selected = false
      )
    )
  }

  fun onCategoryPressed(title: String) {
    val horizontalList = presentationModel.itemBlockCategory.horizontalList?.map { item ->
      val category = item as? ItemCategory
      if (category != null)
        if (category.title == title) category.copy(selected = true) else category.copy(selected = false)
      else item
    }
    presentationModel = presentationModel.copy(
      itemBlockCategory = presentationModel.itemBlockCategory.copy(
        horizontalList = horizontalList
      )
    )
    _data.postValue(presentationModel.mapToDelegateList())
  }

  fun onBestPressed(id: Int) {
    val listBest = presentationModel.bestItems.map { item ->
      val bestItem = item as? ItemBest
      if (bestItem != null) {
        if (bestItem.id == id) {
          if (bestItem.isFavorites) bestItem.copy(isFavorites = false)
          else bestItem.copy(isFavorites = true)
        } else item
      } else item
    }
    presentationModel = presentationModel.copy(
      bestItems = listBest
    )
    _data.postValue(presentationModel.mapToDelegateList())
  }
}

/*
  private fun filtered(): FiltersSelected {
    return FiltersSelected(null, null, null)
  }

  private suspend fun getMainItems(mainScreenData: MainPageResponse): List<ListItem> {
    delay(3000)
    val listItem: MutableList<ListItem> = mutableListOf(
      MapItem { _filter.value = Unit },
      BlockView(
        title = "Select Category",
        more = "view all",
        horizontalList = loadCategory()
      ),
      SearchItem(),
      BlockView(
        title = "Hot sales",
        horizontalList = loadListHotItem(mainScreenData),
        snapOn = true
      ),
      BlockView(
        title = "Best Seller",
        horizontalList = null
      )
    )
    return listItem
  }

  private fun getLoaders(): List<ListItem> {
    return listOf(
      MapItem({}),
      BlockView(
        title = "Select Category",
        more = "view all",
        horizontalList = IntRange(1, 5).map { ProgressCategoryItem }
      ),
      SearchItem(),
      BlockView(
        title = "Hot sales",
        horizontalList = IntRange(1, 1).map { ProgressHotItem }
      ),
      BlockView(
        title = "Best Seller",
        horizontalList = null
      ),
      ProgressBestSellerItem,
      ProgressBestSellerItem,
      ProgressBestSellerItem,
      ProgressBestSellerItem
    )
  }
*/