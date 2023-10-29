package com.example.firebase_config.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.User
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
class AuthViewModel : ViewModel() {

    val authStateLV = MutableLiveData<AuthState>()
    val errorLV = MutableLiveData<ErrorMessage>()


    fun signupWithEmail(name: String, email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(email, pass).await()
                val user = User(name, email, Firebase.auth.currentUser!!.uid, "", "", 0, arrayListOf(), "", 0)
                Firebase.firestore.collection("users")
                    .document(user.userId)
                    .set(user).await()

                withContext(Dispatchers.Main) {
                    authStateLV.value = AuthState(result.user?.uid, true)
                }
                Log.e(">>>", "Registrado")
                Firebase.auth.currentUser
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                withContext(Dispatchers.Main) {
                    errorLV.value = ErrorMessage("La dirección de correo electrónico no es válida")
                }
                Log.e(">>>", "Mal formado")
            } catch (e: FirebaseAuthUserCollisionException) {
                withContext(Dispatchers.Main) {
                    errorLV.value = ErrorMessage("El correo ya está registrado")
                }
                Log.e(">>>", "Repetido")
            } catch (e: FirebaseAuthWeakPasswordException) {
                withContext(Dispatchers.Main) {
                    errorLV.value = ErrorMessage("La contraseña es muy debil")
                }
                Log.e(">>>", "Contraseña muy corta")
            }
        }
    }

//    fun signupWithFacebook(token: AccessToken){
//        FacebookSdk.sdkInitialize(getApplicationContext())
//        viewModelScope.launch(Dispatchers.IO) {
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        Firebase.auth.signInWithCredential(credential).await()
//        Log.d(ContentValues.TAG, "signInWithCredential:success")
//        val user = hashMapOf(
//            "name" to token,
//            "email" to email,
//            "id" to Firebase.auth.currentUser?.uid
//        )
//            Firebase.auth.currentUser
//        }
//    }

        fun signIn(email: String, pass: String) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val result = Firebase.auth.signInWithEmailAndPassword(email, pass).await()
                    Log.e(">>>", "Loggeado")
                    withContext(Dispatchers.Main) {
                        authStateLV.value = AuthState(result.user?.uid, true)
                    }
                } catch (e: Exception) {
                    Log.e(">>>", e.message.toString())
                }
            }
        }

        fun recoverPassword(email: String) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    Firebase.auth.sendPasswordResetEmail(email)
                    Log.e(">>>", "Correo enviado")
                } catch (e: Exception) {
                    Log.e(">>>", "Error")
                }
            }
        }

        fun setUsername(username: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val uid = Firebase.auth.currentUser?.uid

                Firebase.firestore.collection("users")
                    .document(uid!!)
                    .update("username", username).await()
            }
        }


    fun checkPass(pass: String, confirmPass: String): Boolean{
        var samePass = true
        val pass = pass
        val confirmPass = confirmPass
        if(pass != confirmPass){
            samePass = false
        }
        return samePass
    }
}

data class AuthState(
    var userID: String? = null,
    var isAuth: Boolean
)

data class ErrorMessage(
    var message: String
)