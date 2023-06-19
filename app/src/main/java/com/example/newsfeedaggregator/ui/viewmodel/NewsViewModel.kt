package com.example.newsfeedaggregator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsfeedaggregator.data.network.datasource.NewsApiService
import com.example.newsfeedaggregator.data.paging.pagesources.NewsNetworkPagingSource

class NewsViewModel : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = false)
    ) {
        NewsNetworkPagingSource(NewsApiService, "bitcoin")
    }.flow.cachedIn(viewModelScope)
}