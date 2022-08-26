package com.example.testmarket.feature.menu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.feature.cart.domain.CartUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TabViewModel(cartUseCase: CartUseCase) : ViewModel() {
  private val _data = MutableLiveData<Int>()
  val sumProductCart: LiveData<Int> = _data

  init {
    cartUseCase.cartFlow.onEach {
      if (it != null) {
        var count = 0
        it.basket.forEach { item ->
          count += item.sum
        }
        _data.postValue(count)
      }
    }.launchIn(viewModelScope)
  }
}