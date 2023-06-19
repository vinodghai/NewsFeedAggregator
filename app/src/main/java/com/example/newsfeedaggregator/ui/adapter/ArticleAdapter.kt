package com.example.newsfeedaggregator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfeedaggregator.data.network.dtos.ArticleDto
import com.example.newsfeedaggregator.databinding.ItemArticleBinding

class ArticleAdapter :
    PagingDataAdapter<ArticleDto, ArticleAdapter.ArticleViewHolder>(ArticleComparator) {

    companion object ArticleComparator : DiffUtil.ItemCallback<ArticleDto>() {

        override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem == newItem
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleDto?) {
            article?.run {
                binding.run {
                    tvTitle.text = title
                    Glide.with(root).load(urlToImage).into(ivCover)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }
}