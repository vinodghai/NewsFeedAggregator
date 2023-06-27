package com.example.newsfeedaggregator.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeedaggregator.databinding.ActivityNewsBinding
import com.example.newsfeedaggregator.ui.adapter.ArticleAdapter
import com.example.newsfeedaggregator.ui.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagingAdapter = ArticleAdapter()
        with(binding.rvArticles) {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            adapter = pagingAdapter
        }

        lifecycleScope.launch {
            viewModel.newsFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}