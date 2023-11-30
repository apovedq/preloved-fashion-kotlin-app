package com.example.firebase_config.model.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserRepository {

    fun createUserWithEmailAndPassword(email: String, pass: String): Task<AuthResult> {
        return Firebase.auth.createUserWithEmailAndPassword(email, pass)
    }

    fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    fun createUserCollection(user: String): CollectionReference {
        return Firebase.firestore.collection("users")
    }

    fun signInWithEmailAndPassword(email: String, pass: String): Task<AuthResult> {
        return Firebase.auth.signInWithEmailAndPassword(email, pass)
    }

    fun sendPasswordResetEmail(email: String): Task<Void> {
        return Firebase.auth.sendPasswordResetEmail(email)
    }

    fun signInWithCredential(credential: AuthCredential): Task<AuthResult> {
        return Firebase.auth.signInWithCredential(credential)
    }

    fun signOut() {
        Firebase.auth.signOut()
    }


}