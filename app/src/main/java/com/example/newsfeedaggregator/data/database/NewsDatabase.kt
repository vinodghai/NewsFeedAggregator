package com.example.newsfeedaggregator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsfeedaggregator.data.database.dao.ArticleDao
import com.example.newsfeedaggregator.data.database.dao.PageKeyDao
import com.example.newsfeedaggregator.data.database.entities.Article
import com.example.newsfeedaggregator.data.database.entities.PageKey

@Database(entities = [Article::class, PageKey::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun pageKeyDao(): PageKeyDao
}