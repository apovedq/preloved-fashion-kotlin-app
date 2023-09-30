package com.example.firebase_config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase_config.databinding.ActivityMainBinding

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.changeToLoginBTN.setOnClickListener {

            val intent: Intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)

        }

    }
}