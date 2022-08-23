package com.example.testmarket.featureDetails.domain

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.core.network.model.DetailsPageResponse

class UseCaseDetails(private val network: NetworkHolder) {

  suspend fun getData(): DetailsPageResponse {
    return network.api.getDetailsData()
  }
}