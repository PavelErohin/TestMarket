package com.example.testmarket.di

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.featureCart.domain.CartUseCase
import com.example.testmarket.featureDetails.domain.UseCaseDetails
import com.example.testmarket.featureCart.presentation.CartViewModel
import com.example.testmarket.featureDetails.presentation.DetailsViewModel
import com.example.testmarket.featureShop.presentation.viewmodel.ShopViewModel
import com.example.testmarket.featureMenu.TabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  // single оставил пока как сингл
  single { UseCaseDetails(get()) }
  single { CartUseCase(get()) }
  //Simple Presenter Factory
  //factory { MySimplePresenter(get()) }
}

val viewModelModule = module {
  viewModel { DetailsViewModel(get()) }
  viewModel { CartViewModel(get()) }
  viewModel { ShopViewModel(get(), get()) }
  viewModel { TabViewModel(get()) }
}

val networkModule = module {
  single { NetworkHolder() }
}