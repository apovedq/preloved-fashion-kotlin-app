package com.example.firebase_config.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){

    private var fashionPoints = 0

    fun createPost(toString: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            //Validar campos usando ErrorPostMessage!!!
        }
    }

    fun calculateScore(totalProgress: Int) {
        fashionPoints = (100*totalProgress)/25
    }

    fun getFashionPoints(): Int {
        return fashionPoints
    }

}