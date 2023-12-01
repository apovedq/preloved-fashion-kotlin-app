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
import com.example.firebase_config.model.entity.User
import com.example.firebase_config.model.repository.PostRepository
import com.example.firebase_config.model.service.OnFavoritePostSelectedListener
import com.example.firebase_config.model.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID


class ProfileViewModel: ViewModel(), OnFavoritePostSelectedListener {

    val currentUser = Firebase.auth.currentUser
    val userLD = MutableLiveData<User>()

    private val postRepository = PostRepository()
    private val userRepository = UserRepository()
    private val _myposts = MutableLiveData<List<MiniPost>>()
    val myposts: LiveData<List<MiniPost>> get() = _myposts


    fun uploadImage(uri: Uri) {
        try {
            viewModelScope.launch(Dispatchers.IO) {

                //Create random UUID for picture
                var uuid = UUID.randomUUID().toString()

                //Cargar la imagen a storage
                userRepository.profileImage().child(uuid).putFile(uri).await()

                //Update picture url in firebase
                currentUser?.let {
                    userRepository.createUserCollection()
                        .document(it.uid)
                        .update("photo", uuid).await()
                }
            }
        } catch (ex: Exception) {
            Log.e(">>>", ex.message.toString())
        }
    }

    //Function to show user information on profile page
    fun showUser() {

        viewModelScope.launch(Dispatchers.IO) {
            var user: User?

            //Get user info
            currentUser?.let {
                val doc = userRepository.createUserCollection()
                    .document(it.uid)
                    .get().await()
                user = doc.toObject(User::class.java)
                Log.e(">>>", user.toString())
                Log.e(">>>", user?.photo.toString())

                //Get image from storage
                user?.let {
                    Log.e(">>>", user.toString())

                    var url: Uri? = null
                    if (it.photo != null) {
                        try {
                            val storageRef = userRepository.profileImage()
                            val imageRef = storageRef.child(it.photo)
                            url = imageRef.downloadUrl.await()
                        } catch (e: Exception) {
                            Log.e(">>>", "Error downloading image: ${e.message}")
                        }
                    }


                    //Add information from firebase to local variable
                    val localUser = User(
                        it.name,
                        it.email,
                        it.userId,
                        it.username,
                        it.description,
                        it.exchanges,
                        it.favorite,
                        url?.toString() ?: "", // Use an empty string if the URL is null
                        it.rating,
                    )

                    //Variables de Live Data solo se pueden cambiar en hilo del main
                    //Add info from local variable to live data
                    withContext(Dispatchers.Main) { userLD.value = localUser }
                }
            }
        }
    }

    fun setDescription(des: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val uid = userRepository.getCurrentUserId()

            userRepository.createUserCollection()
                .document(uid!!)
                .update("description", des).await()
        }
    }

    fun getPosts(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val posts =postRepository.getPostsByUserId(userId)

            val querySnapshot = posts.get().await()

            loadPosts(querySnapshot)
        }
    }

    fun getFavPosts(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.getFavPosts(userId)?.let { loadPosts(it) }
        }
    }

    private suspend fun loadPosts(querySnapshot: QuerySnapshot) {
        val postsList = mutableListOf<MiniPost>()

        for (document in querySnapshot.documents) {
            val post = document.toObject(Post::class.java)
            val tempPost = MiniPost()

            post?.let {
                var url = ""
                try {
                    url = postRepository.getImage(post.image).toString()
                } catch (e: Exception) {
                    Log.e(">>>", e.message.toString())
                }

                if (isURLValid(url)) {
                    tempPost.image = url
                    tempPost.title = post.title
                    tempPost.fashionPoints = post.fashionPoints
                    postsList.add(tempPost)
                }
            }
        }

        withContext(Dispatchers.Main) {
            _myposts.value = postsList
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
            withContext(Dispatchers.Main) {
                postRepository.getPostsById(post.postId).toObject(Post::class.java)
                    ?.let { postRepository.removeFavoritePost(it) }
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
    }

}
