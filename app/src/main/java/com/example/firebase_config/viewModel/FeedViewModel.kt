package com.example.firebase_config.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {
    private val postRepository = PostRepository()



    fun downloadPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            val postsResponse = postRepository.getFeed()
        }
    }
}