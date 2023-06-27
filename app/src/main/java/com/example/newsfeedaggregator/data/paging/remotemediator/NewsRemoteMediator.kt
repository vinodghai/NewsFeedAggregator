package com.example.newsfeedaggregator.data.paging.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.newsfeedaggregator.data.database.NewsDatabase
import com.example.newsfeedaggregator.data.database.entities.Article
import com.example.newsfeedaggregator.data.database.entities.PageKey
import com.example.newsfeedaggregator.data.network.datasource.NewsApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val newsService: NewsApiService,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, Article>() {

    companion object {
        private const val NEWS_FIRST_PAGE = 1
    }

    private val articleDao = newsDatabase.articleDao()
    private val pageKeyDao = newsDatabase.pageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        try {

            val currentPage: Int = when (loadType) {
                LoadType.REFRESH -> {
                    val currentKey = getRemoteKeyClosestToCurrentPosition(state)
                    currentKey?.currentPage ?: NEWS_FIRST_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKey?.currentPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    if (prevKey == 1)
                        return MediatorResult.Success(endOfPaginationReached = true)
                    prevKey - 1
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKey?.currentPage
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    nextKey + 1
                }
            }

            val response = newsService.getNews(state.config.pageSize, currentPage)
            val articles = response.articles
            val endOfPaginationReached = articles.isEmpty()
            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.clearArticles()
                    pageKeyDao.clearPageKeys()
                }

                val keys = articles.map { article ->
                    PageKey(
                        articleUrl = article.url,
                        currentPage = currentPage
                    )
                }
                articleDao.insertAll(articles)
                pageKeyDao.insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): PageKey? {
        return state.lastItemOrNull()
            ?.let { article ->
                pageKeyDao.pageKeyArticleUrl(articleUrl = article.url)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): PageKey? {
        return state.firstItemOrNull()
            ?.let { article ->
                pageKeyDao.pageKeyArticleUrl(articleUrl = article.url)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): PageKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { articleUrl ->
                pageKeyDao.pageKeyArticleUrl(articleUrl)
            }
        }
    }
}