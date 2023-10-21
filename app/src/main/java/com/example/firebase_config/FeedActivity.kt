package com.example.firebase_config

import PostAdapterFeed
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_config.databinding.ActivityFeedBinding
import com.example.firebase_config.viewModel.FeedViewModel

class FeedActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModels()

    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.downloadPosts()

        viewModel.feed.observe(this){posts ->
            val adapter = PostAdapterFeed(posts)
            binding.feedRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}