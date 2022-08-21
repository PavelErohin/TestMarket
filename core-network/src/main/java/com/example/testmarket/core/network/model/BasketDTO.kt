package com.example.testmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class BasketDTO(
  @SerializedName("id") val id: Int,
  @SerializedName("images") val images: String,
  @SerializedName("price") val price: Int,
  @SerializedName("title") val title: String
)
