package com.example.testmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.core.network.model.DetailsPageResponse
import com.example.testmarket.domain.DetailsUseCase
import com.example.testmarket.model.base.ListItem
import com.example.testmarket.model.main.ItemCapacity
import com.example.testmarket.model.main.ItemColor
import com.example.testmarket.model.main.ItemGallery
import com.example.testmarket.model.main.ScreenDataDetails
import com.example.testmarket.util.MyBaseUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
  private val detailsUseCase: DetailsUseCase
) : ViewModel() {
  private val _data = MutableLiveData<ScreenDataDetails>()
  val dataDetails: LiveData<ScreenDataDetails> = _data

  private val _dataGallery = MutableLiveData<List<ListItem>>()
  val dataGallery: LiveData<List<ListItem>> = _dataGallery

  private val _dataColor = MutableLiveData<List<ItemColor>>()
  val dataColor: LiveData<List<ItemColor>> = _dataColor

  private val _dataCapacity = MutableLiveData<List<ItemCapacity>>()
  val dataCapacity: LiveData<List<ItemCapacity>> = _dataCapacity

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val dataDetails = detailsUseCase.getData()
      _data.postValue(mapDataDetails(dataDetails))
      _dataGallery.postValue(getGallery(dataDetails))
      _dataColor.postValue(getColor(dataDetails))
      _dataCapacity.postValue(getCapacity(dataDetails))
    }
  }

  private fun getGallery(detailsPageResponse: DetailsPageResponse): List<ItemGallery> =
    detailsPageResponse.images.map {
      ItemGallery(images = it)
    }

  private fun getColor(detailsPageResponse: DetailsPageResponse): List<ItemColor> =
    detailsPageResponse.color.map {
      ItemColor(color = it)
    }

  private fun getCapacity(detailsPageResponse: DetailsPageResponse): List<ItemCapacity> =
    detailsPageResponse.capacity.map {
      ItemCapacity(capacity = it)
    }

  private fun mapDataDetails(detailsPageResponse: DetailsPageResponse): ScreenDataDetails {
    return ScreenDataDetails(
      cpu = detailsPageResponse.CPU,
      camera = detailsPageResponse.camera,
      id = detailsPageResponse.id,
      isFavorites = detailsPageResponse.isFavorites,
      price = MyBaseUtil().convertToPrice(detailsPageResponse.price),
      rating = detailsPageResponse.rating,
      sd = detailsPageResponse.sd,
      ssd = detailsPageResponse.ssd,
      title = detailsPageResponse.title
    )
  }

  fun onColorPressed(itemId: Int) {
    _dataColor.value = _dataColor.value?.map {
      if (it.itemId == itemId) it.copy(isSelected = true)
      else it.copy(isSelected = false)
    }
  }

  fun onCapacityPressed(itemId: Int) {
    _dataCapacity.value = _dataCapacity.value?.map {
      if (it.itemId == itemId) it.copy(isSelected = true)
      else it.copy(isSelected = false)
    }
  }

  fun addToFavorites() {
    if (_data.value != null) {
      _data.value = _data.value!!.copy(isFavorites = !(_data.value!!.isFavorites))
    }
  }
}