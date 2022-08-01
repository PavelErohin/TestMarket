package com.example.testmarket.core.network.api

import com.example.testmarket.core.network.model.MainPageResponse
import retrofit2.http.GET

interface TestAPI {
  @GET("/v3/654bd15e-b121-49ba-a588-960956b15175")
  suspend fun getMainPageList(): MainPageResponse
}