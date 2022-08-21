package com.example.testmarket.domain

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.CartPageResponse
import com.example.testmarket.model.main.ItemCart
import com.example.testmarket.model.main.ScreenDataCart
import kotlinx.coroutines.flow.MutableStateFlow

class CartUseCase(private val network: NetworkHolder) {
  //private val network = NetworkHolder()
  val cartFlow = MutableStateFlow<ScreenDataCart?>(null)

  suspend fun getData(): ScreenDataCart {
    return mapCartData(network.api.getCartData())
    //cartFlow.value = mapCartData(network.api.getCartData())
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