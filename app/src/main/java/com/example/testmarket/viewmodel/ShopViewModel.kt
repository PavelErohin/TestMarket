package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.R
import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.MainPageResponse
import com.example.testmarket.domain.CartUseCase
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShopViewModel(
  private val cartUseCase: CartUseCase,
  private val network: NetworkHolder
) : ViewModel() {

  //todo вынести в презенташн модель?

  private val _data = MutableLiveData<List<ListItem>>()
  val data: LiveData<List<ListItem>> = _data

  private var presentationModel = PresentationModelShopScreen()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      cartUseCase.getCartData()//todo вызов не там!?
      val mainScreenData = network.api.getMainListData()
      _data.postValue(presentationModel.mapToDelegateList())
      delay(1000)
      presentationModel = presentationModel.copy(
        itemMap = ItemMap,
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

  private fun PresentationModelShopScreen.mapToDelegateList(): List<ListItem> {
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

  fun onLikeBestPressed(id: Int) {
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