package com.example.testmarket.core.network

import com.example.testmarket.core.network.api.TestAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Network {
  companion object {
    private const val BASE_URL = "https://run.mocky.io/"

    fun createApi(): TestAPI =
      Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
          OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
              level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
              else HttpLoggingInterceptor.Level.NONE
            }).build()
        ).build().create(TestAPI::class.java)
  }
}
