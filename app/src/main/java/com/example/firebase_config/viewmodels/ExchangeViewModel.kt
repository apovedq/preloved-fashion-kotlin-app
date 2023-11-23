package com.example.firebase_config.viewmodels

import android.util.Log
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import com.example.firebase_config.model.repository.PostRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ExchangeViewModel: ViewModel() {

    private val postRepository = PostRepository()
    private val _userPosts = MutableLiveData<List<MiniPost>>()

    val userPosts: LiveData<List<MiniPost>> get() = _userPosts

    private var postToExchange = Post()

    fun downloadPosts(){
        viewModelScope.launch(Dispatchers.IO){

            val posts = postRepository.getPostsByUserId(Firebase.auth.currentUser?.uid.toString())

            val querySnapshot = posts.get().await()
            val postsList = mutableListOf<MiniPost>()

            for (document in querySnapshot.documents){
                val post = document.toObject(Post::class.java)
                val tempPost = MiniPost()

                post?.let {
                    var url = ""
                    try {
                        url = postRepository.getImage(post.image).toString()
                    }catch (e: Exception){
                        Log.e(">>>", e.message.toString())
                    }

                    if(isURLValid(url)){
                        tempPost.image = url
                        tempPost.title = post.title
                        tempPost.fashionPoints = "  ${post.fashionPoints} FP"
                        tempPost.postId = post.postId
                        postsList.add(tempPost)
                    }
                }
            }

            withContext(Dispatchers.Main){
                _userPosts.value = postsList
            }

        }
    }

    private fun isURLValid(url: String): Boolean {
        return url.isNotEmpty() && URLUtil.isNetworkUrl(url)
    }

    fun sendPostToExchangeInfo(newPostToExchange: Post) {
        postToExchange = newPostToExchange
    }

    fun getFashionPoints(): Int {
        return postToExchange.fashionPoints
    }
}