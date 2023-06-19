package com.example.newsfeedaggregator.data.network.interceptors

import com.example.newsfeedaggregator.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .build()
        )
    }
}