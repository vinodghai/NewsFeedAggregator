package com.example.newsfeedaggregator.data.network.api

import com.example.newsfeedaggregator.data.network.dtos.NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything?q=bitcoin")
    suspend fun getNews(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<NewsDto>
}