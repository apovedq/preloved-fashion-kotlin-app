package com.example.firebase_config.model.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class PostRepository {
    companion object {
        const val GET_ALL_POST = "posts"
        const val GET_POST_IMAGE = "postImages"
    }

     fun getPosts(): CollectionReference {
        return Firebase.firestore.collection(GET_ALL_POST)
    }

    suspend fun getPostsById(postId: String): DocumentSnapshot {
        return Firebase.firestore.collection("posts").document(postId).get().await()
    }

    suspend fun getImage(imageId: String): Uri? {
        Log.e(">>>", imageId)
        return Firebase.storage.reference
            .child(GET_POST_IMAGE)
            .child(imageId)
            .downloadUrl.await()
    }
    
}