package com.example.firebase_config.views.fragment.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSignupBinding
import com.example.firebase_config.viewmodels.AuthViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.HomeActivity
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

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
            val name = binding.nameTI.text.toString()
            val email = binding.emailTI.text.toString()
            val password = binding.passwordTI.text.toString()
            val confirmPassword = binding.confirmPasswordTI.text.toString()

            val passChecked = vm.checkPass(password, confirmPassword)

            if (!passChecked) {
                Toast.makeText(requireContext(), "Contraseñas no coinciden", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                vm.signupWithEmail(
                    name,
                    email,
                    password
                )
                val authActivity = activity as AuthActivity
                authActivity.loadFragment(authActivity.usernameFragment)
            }
        }



        binding.facebookBtn.setPermissions("email", "public_profile")
        binding.facebookBtn.setFragment(this)
        val authActivity = activity as AuthActivity

        binding.facebookBtn.registerCallback(
            authActivity.callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$loginResult")
                    vm.signUpWithFacebbook(loginResult.accessToken, loginResult)
                    val authActivity = activity as AuthActivity
                    authActivity.loadFragment(authActivity.usernameFragment)
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                }
            },
        )

        vm.authStateLV.observe(viewLifecycleOwner) { state ->
            if (state.isAuth) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
        vm.errorLV.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignupFragment()
    }
}