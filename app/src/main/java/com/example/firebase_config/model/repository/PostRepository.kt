package com.example.firebase_config.model.repository

import android.net.Uri
import android.util.Log
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostRepository {
    companion object {
        const val GET_ALL_POST = "posts"
        const val GET_POST_IMAGE = "postImages"
    }

     fun getPosts(): CollectionReference {
        return Firebase.firestore.collection(GET_ALL_POST)
    }

    suspend fun userHasPosts(): Boolean {
        return !getPostsByUserId(getCurrentUserId()).get().await().isEmpty
    }

    fun getCurrentUserId(): String{
        return Firebase.auth.currentUser?.uid.toString();
    }

    fun getPostsByUserId(userId: String): Query {
        return Firebase.firestore.collection(GET_ALL_POST)
            .whereEqualTo("userId", userId)
    }

    suspend fun getPostsById(postId: String): DocumentSnapshot {
        return Firebase.firestore.collection("posts").document(postId).get().await()
    }

    suspend fun getImage(imageId: String): Uri? {
        return Firebase.storage.reference
            .child(GET_POST_IMAGE)
            .child(imageId)
            .downloadUrl.await()
    }

    fun uploadFavoritePost(post: Post){
        Firebase.firestore.collection("users").document(getCurrentUserId())
            .update("favorite", FieldValue.arrayUnion(post.postId))
    }

    fun removeFavoritePost(post: Post) {
        Firebase.firestore.collection("users").document(getCurrentUserId())
            .update("favorite", FieldValue.arrayRemove(post.postId))
    }
}