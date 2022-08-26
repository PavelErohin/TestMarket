package com.example.testmarket.feature.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.DetailsPageResponse
import com.example.testmarket.core.util.MyUtil
import com.example.testmarket.feature.details.presentation.models.ItemCapacity
import com.example.testmarket.feature.details.presentation.models.ItemColor
import com.example.testmarket.feature.details.presentation.models.ScreenDataDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class DetailsViewModel(private val network: NetworkHolder) : ViewModel() {

  private val _data = MutableLiveData<ScreenDataDetails>()
  val dataDetails: LiveData<ScreenDataDetails> = _data

  private val _dataColor = MutableLiveData<List<ItemColor>>()
  val dataColor: LiveData<List<ItemColor>> = _dataColor

  private val _dataCapacity = MutableLiveData<List<ItemCapacity>>()
  val dataCapacity: LiveData<List<ItemCapacity>> = _dataCapacity

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val dataDetails = getData()
      _data.postValue(mapDataDetails(dataDetails))
      _dataColor.postValue(getColor(dataDetails))
      _dataCapacity.postValue(getCapacity(dataDetails))
    }
  }

  suspend fun getData(): DetailsPageResponse = network.api.getDetailsData()

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
      images = detailsPageResponse.images.map { CarouselItem(imageUrl = it) },
      isFavorites = detailsPageResponse.isFavorites,
      price = MyUtil().convertToPrice(detailsPageResponse.price),
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