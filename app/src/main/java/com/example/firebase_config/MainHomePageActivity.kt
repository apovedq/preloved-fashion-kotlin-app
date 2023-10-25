package com.example.firebase_config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_config.databinding.ActivityMainHomePageBinding

class MainHomePageActivity : AppCompatActivity() {

    private val binding: ActivityMainHomePageBinding by lazy {
        ActivityMainHomePageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.createPostBtn.setOnClickListener {
            val intent: Intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}