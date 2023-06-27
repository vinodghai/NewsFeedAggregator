package com.example.newsfeedaggregator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedaggregator.data.database.entities.PageKey

@Dao
interface PageKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pageKey: List<PageKey>)

    @Query("SELECT * FROM PageKeys WHERE articleUrl = :articleUrl")
    suspend fun pageKeyArticleUrl(articleUrl: String): PageKey?

    @Query("DELETE FROM PageKeys")
    suspend fun clearPageKeys()
}