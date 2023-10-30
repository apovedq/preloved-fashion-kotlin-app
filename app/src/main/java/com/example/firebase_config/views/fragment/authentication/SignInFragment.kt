package com.example.firebase_config.views.fragment.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSignInBinding
import com.example.firebase_config.viewmodels.AuthViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.HomeActivity

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)


        binding.registerLink.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signupFragment)
        }

        binding.logInBTN.setOnClickListener {
            val userEmail = binding.editMailPT.text.toString()
            val pass = binding.editPasswordPT.text.toString()
            vm.signIn(userEmail, pass)
        }

        binding.forgotPassTV.setOnClickListener{
            val currentMail = binding.editMailPT.text.toString()
            vm.recoverPassword(currentMail)
        }

        vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}