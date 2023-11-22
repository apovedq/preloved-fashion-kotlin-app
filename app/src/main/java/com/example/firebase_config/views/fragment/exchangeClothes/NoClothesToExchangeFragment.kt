package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.NoClothesToExchangeFragmentBinding

class NoClothesToExchangeFragment: Fragment() {

    private lateinit var binding: NoClothesToExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NoClothesToExchangeFragmentBinding.inflate(inflater, container,false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object{
        fun newInstance(): NoClothesToExchangeFragment{
            return NoClothesToExchangeFragment()
        }
    }

}