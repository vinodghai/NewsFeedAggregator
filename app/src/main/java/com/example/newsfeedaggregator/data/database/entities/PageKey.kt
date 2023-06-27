package com.example.newsfeedaggregator.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PageKeys")
data class PageKey(
    @PrimaryKey
    val articleUrl: String,
    val currentPage: Int
)