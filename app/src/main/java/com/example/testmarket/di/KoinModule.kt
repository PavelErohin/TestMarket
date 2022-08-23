package com.example.testmarket.di

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.domain.CartUseCase
import com.example.testmarket.domain.DetailsUseCase
import com.example.testmarket.viewmodel.CartViewModel
import com.example.testmarket.viewmodel.DetailsViewModel
import com.example.testmarket.viewmodel.ShopViewModel
import com.example.testmarket.viewmodel.TabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  // single оставил пока как сингл
  single { DetailsUseCase(get()) }
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