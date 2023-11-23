package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.SuccessfulExchangeFragmentBinding

class SuccessfulExchangeFragment: Fragment() {

    private lateinit var binding: SuccessfulExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SuccessfulExchangeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object{
        fun newInstance(): SuccessfulExchangeFragment{
            return SuccessfulExchangeFragment()
        }
    }
}