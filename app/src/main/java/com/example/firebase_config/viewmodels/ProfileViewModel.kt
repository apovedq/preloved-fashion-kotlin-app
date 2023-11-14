package com.example.firebase_config.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID

class ProfileViewModel: ViewModel() {

    val currentUser = Firebase.auth.currentUser
    val userLD = MutableLiveData<User>()

    fun uploadImage(uri: Uri) {
        try {
            viewModelScope.launch(Dispatchers.IO) {

                //Create random UUID for picture
                var uuid = UUID.randomUUID().toString()

                //Cargar la imagen a storage
                Firebase.storage.reference.child("profileImages").child(uuid).putFile(uri).await()

                //Update picture url in firebase
                currentUser?.let {
                    Firebase.firestore.collection("users")
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
                val doc = Firebase.firestore
                    .collection("users")
                    .document(it.uid)
                    .get().await()
                user = doc.toObject(User::class.java)
                Log.e(">>>", user.toString())
                Log.e(">>>", user?.photo.toString())

                //Get image from storage
                user?.let {
                    Log.e(">>>", user.toString())
                    var url = Firebase.storage.reference.child("profileImages")
                        .child(it.photo).downloadUrl.await()

                    //Add information from firebase to local variable
                    val localUser = User(
                        it.name,
                        it.email,
                        it.userId,
                        it.username,
                        it.description,
                        it.exchanges,
                        it.favorite,
                        url.toString(),
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
            val uid = Firebase.auth.currentUser?.uid

            Firebase.firestore.collection("users")
                .document(uid!!)
                .update("description", des).await()
        }
    }
}
