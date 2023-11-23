package com.example.firebase_config.views.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.FeedFragmentBinding
import com.example.firebase_config.databinding.FragmentSearchBinding
import com.example.firebase_config.views.fragment.authentication.SignInFragment
import com.example.firebase_config.views.fragment.profile.DescriptionFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        return binding.root
    }




    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

}

