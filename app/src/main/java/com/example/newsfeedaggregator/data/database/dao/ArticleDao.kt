package com.example.newsfeedaggregator.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeedaggregator.data.database.entities.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Query(
        "SELECT * FROM Articles a inner join PageKeys pk on a.url = pk.articleUrl " +
                "order by pk.currentPage asc"
    )
    fun getAll(): PagingSource<Int, Article>

    @Query("DELETE FROM Articles")
    suspend fun clearArticles()
}