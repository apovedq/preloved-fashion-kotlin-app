package com.example.firebase_config.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.views.fragment.authentication.SignInFragment
import com.example.firebase_config.databinding.ActivityAuthBinding
import com.example.firebase_config.views.fragment.authentication.AuthFragment
import com.example.firebase_config.views.fragment.authentication.SignupFragment
import com.example.firebase_config.views.fragment.authentication.UsernameFragment
import com.facebook.CallbackManager

class AuthActivity : AppCompatActivity() {

    val callbackManager = CallbackManager.Factory.create();

    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    val authFragment by lazy {
        AuthFragment.newInstance()
    }

    val signupFragment by lazy {
        SignupFragment.newInstance()
    }

    val signinFragment by lazy {
        SignInFragment.newInstance()
    }

    val usernameFragment by lazy {
        UsernameFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(authFragment)
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment).commit()
    }
}