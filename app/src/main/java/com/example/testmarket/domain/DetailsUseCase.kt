package com.example.testmarket.domain

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.DetailsPageResponse

class DetailsUseCase(private val network: NetworkHolder) {

  suspend fun getData(): DetailsPageResponse {
    return network.api.getDetailsData()
  }
}