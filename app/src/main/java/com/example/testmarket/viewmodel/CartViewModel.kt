package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.domain.CartUseCase
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {
  private val _data = MutableLiveData<List<ListItem>>()
  val dataCart: LiveData<List<ListItem>> = _data
  private var presentationModel = PresentationModelMainScreen()

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val cartData = getMainItems(cartUseCase.getData())
      _data.postValue(cartData)
    }
  }


  private fun getMainItems(screenDataCart: ScreenDataCart): List<ListItem> =
    listOf(ItemCartTop) +
        screenDataCart.basket +
        listOf(
          ItemCartBottom(
            delivery = screenDataCart.delivery,
            total = screenDataCart.total
          )
        )

  fun plusClick(id: Int) {/*
    val data = _data.copy { item ->
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
    _data.postValue(presentationModel.mapToDelegateList())*/
  }
    fun minusClick(id: Int) {
      //todo
    }

    fun deleteClick(id: Int) {
      //todo
    }
  }