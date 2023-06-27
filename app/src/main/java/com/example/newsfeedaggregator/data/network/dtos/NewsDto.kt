package com.example.newsfeedaggregator.data.network.dtos

import com.example.newsfeedaggregator.data.database.entities.Article
import java.io.Serializable

data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Serializable