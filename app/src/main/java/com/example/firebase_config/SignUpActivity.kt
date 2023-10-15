package com.example.firebase_config

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_config.databinding.ActivitySignUpBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            Firebase.auth.createUserWithEmailAndPassword(
                binding.emailTI.text.toString(),
                binding.passwordTI.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainHomePageActivity::class.java))
                finish()

            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}