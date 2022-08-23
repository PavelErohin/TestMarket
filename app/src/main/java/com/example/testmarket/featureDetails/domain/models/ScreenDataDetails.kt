package com.example.testmarket.featureDetails.domain.models

data class ScreenDataDetails(
  val cpu: String,
  val camera: String,
  val id: Int,
  val isFavorites: Boolean,
  val price: String,
  val rating: Float,
  val sd: String,
  val ssd: String,
  val title: String
)
