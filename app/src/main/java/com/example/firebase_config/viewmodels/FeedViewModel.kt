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
import com.example.firebase_config.model.service.OnFavoritePostSelectedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FeedViewModel: ViewModel(), OnFavoritePostSelectedListener {
    private val postRepository = PostRepository()
    private val _feed = MutableLiveData<List<MiniPost>>()
    val feed: LiveData<List<MiniPost>> get() = _feed
    val postDetailId: String? = null

    //Stores the post data in my varibale
    val postInfo = MutableLiveData<Post?>()

    //Stores the img url
    val imageUrl = MutableLiveData<Uri?>()

    //Stores all posts
    private var allPosts: List<MiniPost> = emptyList()


    fun downloadPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            val posts = postRepository.getPosts()
            val currentUser = postRepository.getCurrentUserId();

            val querySnapshot = posts.get().await()
            val postsList = mutableListOf<MiniPost>()

            for (document in querySnapshot.documents){
                val post = document.toObject(Post::class.java)

                post?.let {
                    if(post.userId != currentUser){
                        val tempPost = MiniPost()
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
            }
            allPosts = postsList // Almacena todos los posts
            withContext(Dispatchers.Main){
                _feed.value = postsList
            }
        }
    }
    // Agrega el método de filtrado
    fun filterPostsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val filteredList = if (category == "Todas") {
                allPosts
            } else {
                allPosts.filter { it.category.equals(category, ignoreCase = true) }
            }
            _feed.value = filteredList
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

    override fun onPostSelected(post: MiniPost) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                postRepository.getPostsById(post.postId).toObject(Post::class.java)
                    ?.let { postRepository.uploadFavoritePost(it) }
            }
        }
    }

    override fun onPostDeselected(post: MiniPost) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                postRepository.getPostsById(post.postId).toObject(Post::class.java)
                    ?.let { postRepository.removeFavoritePost(it) }
            }
        }
    }
}