package com.example.newsfeedaggregator.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeedaggregator.databinding.ActivityMainBinding
import com.example.newsfeedaggregator.ui.adapter.ArticleAdapter
import com.example.newsfeedaggregator.ui.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagingAdapter = ArticleAdapter()
        with(binding.rvArticles) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pagingAdapter
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}