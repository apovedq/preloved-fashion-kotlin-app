package com.example.firebase_config.model.repository

import android.util.Log
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.model.entity.MiniPost
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PostRepository {
    companion object {
        const val GET_ALL_POST = "posts"
    }

    suspend fun getFeed(){
        val posts = Firebase.firestore
            .collection(GET_ALL_POST)
        try {
            val querySnapshot = posts.get().await()
            val postsList = mutableListOf<Post>()

            for (document in querySnapshot.documents){
                val post = document.toObject(Post::class.java)
                post?.let {
                    postsList.add(it)
                }
            }

            for (post in postsList){
                Log.e(">>>", "Post: ${post.title}")
            }
        } catch (e: Exception) {
            // Manejo de errores, por ejemplo, imprimir el mensaje de error
            Log.e(">>>", "Error al obtener los posts: ${e.message}")
        }
    }

}