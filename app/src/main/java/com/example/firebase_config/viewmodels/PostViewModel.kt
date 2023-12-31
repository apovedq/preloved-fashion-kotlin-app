package com.example.firebase_config.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.repository.PostRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID


class PostViewModel : ViewModel() {

    private var newPost = Post()

    val currentUriToStore = MutableLiveData<Uri>()

    fun collectPostInformation(
        category: String,
        gender: String,
        title: String,
        brand: String,
        size: String,
        description: String,
        image: Uri?
    ) {
        newPost.postId = UUID.randomUUID().toString()
        newPost.category = category
        newPost.gender = gender
        newPost.title = title
        newPost.brand = brand
        newPost.size = size
        newPost.description = description
        newPost.image = image.toString()
        newPost.userId = Firebase.auth.currentUser?.uid.toString()
    }

    private fun createPost(newPost: Post) {
        viewModelScope.launch(Dispatchers.IO) {

            //Upload image to firestore when validate it
            currentUriToStore.value?.let { uploadImageToFirestore(it, newPost.postId) }
            newPost.image = newPost.postId

            //Upload the post to db
            Firebase.firestore.collection("posts").document(newPost.postId).set(newPost)
        }
    }

    private suspend fun uploadImageToFirestore(uri: Uri, postId: String) {
        try {
            Firebase.storage.reference.child("postImages").child(postId)
                .putFile(uri).await()
            Log.e(">>>", "Image upload succesfully")
        } catch (ex: Exception) {
            Log.e(">>>", ex.message.toString())
        }
    }


    fun calculateScore(totalProgress: Int) {
        val score = (100 * totalProgress) / 25
        newPost.fashionPoints = score
        createPost(newPost)
    }

    fun getFashionPoints(): Int {
        return newPost.fashionPoints
    }

}