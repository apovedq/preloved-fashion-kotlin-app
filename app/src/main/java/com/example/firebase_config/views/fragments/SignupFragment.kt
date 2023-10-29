package com.example.firebase_config.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSignupBinding
import com.example.firebase_config.viewmodels.AuthViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.MainHomePageActivity

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val vm: AuthViewModel by activityViewModels()
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.loginLink.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signinFragment)
        }

        binding.registerBtn.setOnClickListener {
            val name= binding.nameTI.text.toString()
            val email = binding.emailTI.text.toString()
            val password = binding.passwordTI.text.toString()
            vm.signupWithEmail(
                name,
                email,
                password
            )
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.usernameFragment)
        }

        vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), MainHomePageActivity::class.java))
            }
        }
        vm.errorLV.observe(viewLifecycleOwner){error->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }
}