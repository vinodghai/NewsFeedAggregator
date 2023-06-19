package com.example.newsfeedaggregator.data.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newsfeedaggregator.App
import com.example.newsfeedaggregator.data.network.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            ChuckerInterceptor.Builder(App.context)
                .collector(ChuckerCollector(App.context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(RequestInterceptor)
        .build()

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}