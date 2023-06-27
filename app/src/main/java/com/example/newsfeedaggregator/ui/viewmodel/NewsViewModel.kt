package com.example.newsfeedaggregator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsfeedaggregator.App
import com.example.newsfeedaggregator.data.network.datasource.NewsApiService
import com.example.newsfeedaggregator.data.paging.remotemediator.NewsRemoteMediator

class NewsViewModel : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 20
        private const val INITIAL_PAGES = 2
    }

    @OptIn(ExperimentalPagingApi::class)
    val newsFlow = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_PAGES * PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { App.db.articleDao().getAll() },
        remoteMediator = NewsRemoteMediator(
            NewsApiService, App.db
        )
    ).flow.cachedIn(viewModelScope)
}