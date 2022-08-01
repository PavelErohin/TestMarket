package com.example.testmarket.core.network.model

import com.google.gson.annotations.SerializedName

data class HotDTO(
  @SerializedName("id") val id: Int,
  @SerializedName("is_new") val isNew: Boolean = false,
  @SerializedName("title") val title: String,
  @SerializedName("subtitle") val subtitle: String,
  @SerializedName("picture") val picture: String,
  @SerializedName("is_buy") val isBuy: Boolean
)
