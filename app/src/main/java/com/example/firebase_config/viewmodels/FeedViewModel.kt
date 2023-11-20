package com.example.firebase_config.viewmodels

import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import com.example.firebase_config.model.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FeedViewModel: ViewModel() {
    private val postRepository = PostRepository()
    private val _feed = MutableLiveData<List<MiniPost>>()
    val feed: LiveData<List<MiniPost>> get() = _feed
    val postDetailId: String? = null

    //Stores the post data in my varibale
    val postInfo = MutableLiveData<Post?>()

    //Stores the img url
    val imageUrl = MutableLiveData<Uri?>()

    fun downloadPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            val posts = postRepository.getPosts()

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
                _feed.value = postsList
            }
        }
    }

    fun findPostById(postId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val posts = postRepository.getPostsById(postId)
            val currentPost = posts.toObject(Post::class.java)


            withContext(Dispatchers.Main){
                postInfo.value = currentPost
            }

            //Get url
            postInfo.value?.let {
                val currentUrl: Uri? = postRepository.getImage(it.postId)

                withContext(Dispatchers.Main){
                    imageUrl.value = currentUrl
                }
            }
            //Notify view with the live data
        }
    }

    private fun isURLValid(url: String): Boolean {
        return url.isNotEmpty() && URLUtil.isNetworkUrl(url)
    }
}