package com.example.newsfeedaggregator.data.network.datasource

import com.example.newsfeedaggregator.data.network.RetrofitClient
import com.example.newsfeedaggregator.data.network.api.NewsApi
import com.example.newsfeedaggregator.data.network.dtos.NewsDto
import java.io.IOException

object NewsApiService {

    private val retrofit = RetrofitClient.getClient()
    private val newsApi = retrofit.create(NewsApi::class.java)

    suspend fun getNews(q: String, pageSize: Int, page: Int): NewsDto {
        val newsResponse = newsApi.getNews(q, pageSize, page)
        if (newsResponse.isSuccessful && newsResponse.body() != null)
            return newsResponse.body()!!
        throw IOException(newsResponse.message())
    }
}