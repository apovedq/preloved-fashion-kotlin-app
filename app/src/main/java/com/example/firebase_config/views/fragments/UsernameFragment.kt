package com.example.firebase_config.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSetUsernameBinding
import com.example.firebase_config.databinding.FragmentSignInBinding
import com.example.firebase_config.viewmodels.AuthViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.MainHomePageActivity

class UsernameFragment: Fragment() {
    private lateinit var binding: FragmentSetUsernameBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetUsernameBinding.inflate(inflater, container, false)

        binding.setUsernameBtn.setOnClickListener {
            val username = binding.setUsernameTv.text.toString()
            vm.setUsername(username)

            vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), MainHomePageActivity::class.java)) }
            }
        }

//

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsernameFragment()
    }
}