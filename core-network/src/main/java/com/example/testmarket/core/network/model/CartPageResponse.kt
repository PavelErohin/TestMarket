package com.example.testmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class CartPageResponse(
  @SerializedName("basket") val basket: List<BasketDTO>,
  @SerializedName("delivery") val delivery: String,
  @SerializedName("id") val id: Int,
  @SerializedName("total") val total: Int
)