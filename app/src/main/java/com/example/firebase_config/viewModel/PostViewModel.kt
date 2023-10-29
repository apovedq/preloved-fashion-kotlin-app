package com.example.firebase_config.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.Post
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class PostViewModel : ViewModel(){

    private var newPost = Post()

     private fun createPost(newPost: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("posts").document(newPost.postId).set(newPost)
        }
    }

    fun collectPostInformation(
        category: String,
        gender: String,
        title: String,
        brand: String,
        size: String,
        description: String,
        image: String
    ) {
        newPost.postId = UUID.randomUUID().toString()
        newPost.category = category
        newPost.gender = gender
        newPost.title = title
        newPost.brand = brand
        newPost.size = size
        newPost.description = description
        newPost.image = image
        newPost.userId = Firebase.auth.currentUser?.uid.toString()
    }

    fun calculateScore(totalProgress: Int) {
        val score = (100*totalProgress)/25
        newPost.fashionPoints = score
        createPost(newPost)
    }

    fun getFashionPoints(): Int {
        return newPost.fashionPoints
    }

}