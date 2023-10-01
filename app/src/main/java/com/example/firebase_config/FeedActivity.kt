package com.example.firebase_config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_config.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}