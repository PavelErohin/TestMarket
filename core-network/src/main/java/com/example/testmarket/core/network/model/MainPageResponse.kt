package com.example.testmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class MainPageResponse(
  @SerializedName("home_store") val hotList: List<HotDTO>,
  @SerializedName("best_seller") val bestList: List<BestDTO>
)
