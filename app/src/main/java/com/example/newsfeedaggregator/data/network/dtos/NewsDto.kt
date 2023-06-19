package com.example.newsfeedaggregator.data.network.dtos

import java.io.Serializable

data class NewsDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
) : Serializable