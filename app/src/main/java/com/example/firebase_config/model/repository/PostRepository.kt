package com.example.firebase_config.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PostRepository {
    companion object {
        const val GET_ALL_POST = "posts"
    }

     fun getFeed(): CollectionReference {
        return Firebase.firestore.collection(GET_ALL_POST)
    }
}