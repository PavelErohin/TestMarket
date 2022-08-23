package com.example.testmarket.featureCart.domain

import com.example.testmarket.featureCart.domain.models.ItemCart
import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.CartPageResponse
import com.example.testmarket.featureCart.domain.models.ScreenDataCart
import kotlinx.coroutines.flow.MutableStateFlow

class CartUseCase(private val network: NetworkHolder) {
  val cartFlow = MutableStateFlow<ScreenDataCart?>(null)

  suspend fun getCartData() {
    cartFlow.value = mapCartData(network.api.getCartData())
  }

  fun changeCartAmount(id: Int, isPlus: Boolean) {
    cartFlow.value = cartFlow.value?.copy(
      basket = cartFlow.value?.basket?.map {
        if (id == it.id) {
          it.copy(
            sum = it.sum + if (isPlus) 1 else {
              if (it.sum <= 0) 0 else -1
            }
          )
        } else it
      } ?: emptyList())

  }

  fun deleteCartItem(id: Int) {
    cartFlow.value = cartFlow.value?.copy(
      basket = cartFlow.value?.basket?.filter { id != it.id } ?: emptyList())
  }

  private fun mapCartData(cartPageResponse: CartPageResponse): ScreenDataCart {
    return ScreenDataCart(
      basket = cartPageResponse.basket.map {
        ItemCart(
          id = it.id,
          images = it.images,
          price = it.price,
          title = it.title
        )
      },
      delivery = cartPageResponse.delivery,
      id = cartPageResponse.id,
      total = cartPageResponse.total
    )
  }
}