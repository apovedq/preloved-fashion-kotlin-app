package com.example.firebase_config.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.firebase_config.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(3000)
            val user = Firebase.auth.currentUser
            val text = "No hay usuario"
            if (user == null) {
                startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
            }

        }
    }
}