package com.example.testmarket.di

import com.example.testmarket.core.network.NetworkHolder
import com.example.testmarket.feature.cart.domain.CartUseCase
import com.example.testmarket.feature.cart.presentation.CartViewModel
import com.example.testmarket.feature.details.presentation.DetailsViewModel
import com.example.testmarket.feature.menu.presentation.TabViewModel
import com.example.testmarket.feature.shop.presentation.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single { CartUseCase(get()) }
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