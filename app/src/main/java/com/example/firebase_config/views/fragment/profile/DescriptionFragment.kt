package com.example.firebase_config.views.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.SetDescriptionFragmentBinding
import com.example.firebase_config.viewmodels.ProfileViewModel
import com.example.firebase_config.views.HomeActivity

class DescriptionFragment: Fragment() {

    private lateinit var binding: SetDescriptionFragmentBinding
    val vm: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SetDescriptionFragmentBinding.inflate(inflater, container, false)

        binding.setDescriptionBtn.setOnClickListener {
            //Get text and send to firebase
            val description = binding.desInput.text.toString()
            vm.setDescription(description)

            //Send back to profile page
            val homeActivity = activity as HomeActivity
            homeActivity.loadFragment(homeActivity.profileFragment)
        }

        return binding.root
    }

    companion object {
        fun newInstance():DescriptionFragment {
            return DescriptionFragment()
        }
    }

}