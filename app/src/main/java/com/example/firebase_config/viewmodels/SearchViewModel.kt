package com.example.firebase_config.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){

    private val _searchView = MutableLiveData<List<MiniPost>>()
    val searchView: LiveData<List<MiniPost>> get() = _searchView


//    fun searchClothes(posts : Post, input : String){
//        viewModelScope.launch(Dispatchers.Main) {
//            val filteredList = if (input == "Todas") {
//                posts
//            } else {
//                posts.filter { it.category.equals(input, ignoreCase = true) }
//            }
//            _feed.value = filteredList
//        }
//
//    }

}