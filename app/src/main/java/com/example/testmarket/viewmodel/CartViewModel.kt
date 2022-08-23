package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.domain.CartUseCase
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCartBottom
import com.example.testmarket.model.main.ItemCartTop
import com.example.testmarket.model.main.ScreenDataCart
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {
  private val _data = MutableLiveData<List<ListItem>>()
  val dataCart: LiveData<List<ListItem>> = _data

  init {
    cartUseCase.cartFlow.onEach {
      if (it != null) {
        _data.postValue(getMainItems(it))
      }
    }.launchIn(viewModelScope)
  }

  private fun getMainItems(screenDataCart: ScreenDataCart): List<ListItem> =
    listOf(ItemCartTop) + screenDataCart.basket +
        listOf(
          ItemCartBottom(delivery = screenDataCart.delivery, total = screenDataCart.total)
        )

  fun plusClick(id: Int) {
    cartUseCase.changeCartAmount(id, true)
  }

  fun minusClick(id: Int) {
    cartUseCase.changeCartAmount(id, false)
  }

  fun deleteClick(id: Int) {
    cartUseCase.deleteCartItem(id)
  }
}