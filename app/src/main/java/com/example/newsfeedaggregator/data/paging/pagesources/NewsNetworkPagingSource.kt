package com.example.newsfeedaggregator.data.paging.pagesources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsfeedaggregator.data.network.datasource.NewsApiService
import com.example.newsfeedaggregator.data.network.dtos.ArticleDto

class NewsNetworkPagingSource(
    private val newsApiService: NewsApiService,
    private val q: String,
) : PagingSource<Int, ArticleDto>() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: FIRST_PAGE
            val response = newsApiService.getNews(q, params.loadSize, nextPageNumber)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (params.key == null) null else nextPageNumber - 1,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}