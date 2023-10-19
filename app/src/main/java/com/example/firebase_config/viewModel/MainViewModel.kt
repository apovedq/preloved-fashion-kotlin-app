package com.example.firebase_config.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    companion object {
        const val GET_ALL_POST = "/posts"
    }

    fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}