package com.example.testmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class BestDTO(
  @SerializedName("id") val id: Int,
  @SerializedName("is_favorites") val isFavorites: Boolean = false,
  @SerializedName("title") val title: String,
  @SerializedName("price_without_discount") val priceWithoutDiscount: Int,
  @SerializedName("discount_price") val discountPrice: Int,
  @SerializedName("picture") val picture: String
)
