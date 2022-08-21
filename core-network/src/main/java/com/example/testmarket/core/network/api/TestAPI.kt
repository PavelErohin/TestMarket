package com.example.testmarket.core.network.api

import com.example.testmarket.core.network.model.DetailsPageResponse
import com.example.testmarket.core.network.model.MainPageResponse
import com.example.testmarket.core.network.model.CartPageResponse
import retrofit2.http.GET

interface TestAPI {
  @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
  suspend fun getMainListData(): MainPageResponse

  @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
  suspend fun getDetailsData(): DetailsPageResponse

  @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
  suspend fun getCartData(): CartPageResponse
}