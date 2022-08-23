package com.example.testmarket.featureCart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.featureCart.domain.CartUseCase
import com.example.testmarket.core.model.ListItem
import com.example.testmarket.featureCart.domain.models.ItemCartBottom
import com.example.testmarket.featureCart.domain.models.ItemCartTop
import com.example.testmarket.featureCart.domain.models.ScreenDataCart
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {
  private val _data = MutableLiveData<List<ListItem>>()
  val dataCart: LiveData<List<ListItem>> = _data

  init {
    cartUseCase.cartFlow.onEach {
      if (it != null) {
        var count = 0
        it.basket.forEach { item ->
          count += item.sum * item.price
        }
        _data.postValue(getMainItems(it, count))
      }
    }.launchIn(viewModelScope)
  }

  private fun getMainItems(screenDataCart: ScreenDataCart, total: Int): List<ListItem> =
    listOf(ItemCartTop) + screenDataCart.basket +
        listOf(
          ItemCartBottom(delivery = screenDataCart.delivery, total = total)
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