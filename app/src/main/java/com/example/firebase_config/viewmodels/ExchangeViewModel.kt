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
import com.example.firebase_config.model.service.OnPostSelectedListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ExchangeViewModel: ViewModel(), OnPostSelectedListener {

    private val postRepository = PostRepository()
    private val _userPosts = MutableLiveData<List<MiniPost>>()
    private val _postToExchange = MutableLiveData<Post>()
    private val _postsSelected = MutableLiveData<List<MiniPost>>()
    private val _fashionPoints = MutableLiveData<Int>()

    val userPosts: LiveData<List<MiniPost>> get() = _userPosts
    val postToExchange: LiveData<Post> get() = _postToExchange
    val postsSelected: LiveData<List<MiniPost>> get() = _postsSelected
    val fashionPoints: LiveData<Int> get() = _fashionPoints

    fun downloadPosts(){
        viewModelScope.launch(Dispatchers.IO){

            val posts = postRepository.getPostsByUserId(postRepository.getCurrentUserId())

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
                        tempPost.fashionPoints = post.fashionPoints
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

    fun getPostById(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val post = postRepository.getPostsById(id).toObject(Post::class.java)
            post.let {
                withContext(Dispatchers.Main){
                    _postToExchange.value = it
                }
            }
        }
    }

    private fun getFashionPointsFromSelectedPosts() {
        var total = 0
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                _postsSelected.value?.filter { it.isSelected }?.forEach{ total += it.fashionPoints }
                _fashionPoints.value = total
            }
        }
    }

    override fun onPostSelected(posts: List<MiniPost>) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                _postsSelected.value = posts
                getFashionPointsFromSelectedPosts()
            }
        }
    }
}