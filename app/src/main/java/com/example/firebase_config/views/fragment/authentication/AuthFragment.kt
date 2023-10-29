package com.example.firebase_config.views.fragment.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.FragmentAuthBinding
import com.example.firebase_config.views.AuthActivity

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        binding.goToRegisterBtn.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signupFragment)
        }


        binding.changeToLoginBTN.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signinFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

}