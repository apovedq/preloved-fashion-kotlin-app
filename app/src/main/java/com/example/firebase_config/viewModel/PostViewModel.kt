package com.example.firebase_config.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.ErrorPostMessage
import com.example.firebase_config.model.dto.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){

    private val postLV = MutableLiveData<Post>()
    private val errorPostMessageLV = MutableLiveData<ErrorPostMessage>()

    fun createPost(toString: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            //Validar campos usando ErrorPostMessage!!!
        }
    }

}