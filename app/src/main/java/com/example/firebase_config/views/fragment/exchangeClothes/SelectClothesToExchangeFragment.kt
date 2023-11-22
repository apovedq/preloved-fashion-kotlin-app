package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.SelectClothesToExchangeFragmentBinding

class SelectClothesToExchangeFragment: Fragment() {

    private lateinit var binding: SelectClothesToExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectClothesToExchangeFragmentBinding.inflate(inflater, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object{
        fun newInstance(): SelectClothesToExchangeFragment{
            return SelectClothesToExchangeFragment()
        }
    }
}