package com.example.firebase_config.viewmodels

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.entity.User
import com.example.firebase_config.viewmodels.model.AuthState
import com.example.firebase_config.viewmodels.model.ErrorMessage
import com.example.firebase_config.viewmodels.model.FacebookDataCallBack
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.Profile
import com.facebook.Profile.Companion.getCurrentProfile
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.json.JSONException
import org.json.JSONObject


class AuthViewModel : ViewModel() {

    val authStateLV = MutableLiveData<AuthState>()
    val errorLV = MutableLiveData<ErrorMessage>()


    fun signupWithEmail(name: String, email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(email, pass).await()
                val user = User(
                    name,
                    email,
                    Firebase.auth.currentUser!!.uid,
                    "",
                    "",
                    0,
                    arrayListOf(),
                    "",
                    0
                )
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


    fun checkPass(pass: String, confirmPass: String): Boolean {
        var samePass = true
        val pass = pass
        val confirmPass = confirmPass
        if (pass != confirmPass) {
            samePass = false
        }
        return samePass
    }

    private fun setFacebookData(loginResult: LoginResult, callback: FacebookDataCallBack) {
        val request = GraphRequest.newMeRequest(
            loginResult.accessToken,
            object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {
                    try {
                        val email = response!!.getJSONObject()!!.getString("email")
                        val name = response.getJSONObject()!!.getString("name")
                        callback.onDataReceived(name, email)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
        val parameters = Bundle()
        parameters.putString("fields", "id,email,name")
        request.parameters = parameters
        request.executeAsync()
    }

    fun signUpWithFacebbook(token: AccessToken, loginResult: LoginResult) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = Firebase.auth.signInWithCredential(credential).await()
                setFacebookData(loginResult, object : FacebookDataCallBack {
                    override fun onDataReceived(name: String, email: String) {
                        val user = User(
                            name,
                            email,
                            Firebase.auth.currentUser!!.uid,
                            "",
                            "",
                            0,
                            arrayListOf(),
                            "",
                            0
                        )
                        Log.e(">>>", user.toString())
                        Firebase.firestore.collection("users")
                            .document(user.userId)
                            .set(user)
                    }
                })

                withContext(Dispatchers.Main) {
                    authStateLV.value = AuthState(result.user?.uid, true)
                }

                Log.e(">>>", "Registrado")
                Log.e(">>>>", result.credential.toString());

            } catch (e: FirebaseAuthInvalidCredentialsException) {
                withContext(Dispatchers.Main) {
                    errorLV.value =
                        ErrorMessage("La dirección de correo electrónico no es válida")
                }
                Log.e(">>>", "Mal formado")
            }
        }
    }


    fun signInWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = Firebase.auth.signInWithCredential(credential).await()

                withContext(Dispatchers.Main) {
                    authStateLV.value = AuthState(result.user?.uid, true)
                }

                Log.e(">>>", "Registrado")
                Log.e(">>>>", result.credential.toString());

            } catch (e: FirebaseAuthInvalidCredentialsException) {
                Log.e(">>>", e.message.toString())
            }
        }
    }
}
