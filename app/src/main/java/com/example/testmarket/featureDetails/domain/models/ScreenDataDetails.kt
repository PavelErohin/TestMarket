package com.example.testmarket.featureDetails.domain.models

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

data class ScreenDataDetails(
  val cpu: String,
  val camera: String,
  val id: Int,
  val images: List<CarouselItem>,
  val isFavorites: Boolean,
  val price: String,
  val rating: Float,
  val sd: String,
  val ssd: String,
  val title: String
)
