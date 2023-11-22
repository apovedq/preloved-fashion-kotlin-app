package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.HowExchangeWorksFragmentBinding
import com.example.firebase_config.databinding.SuccessfulExchangeFragmentBinding

class HowExchangeWorksFragment: Fragment() {

    private lateinit var binding: HowExchangeWorksFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HowExchangeWorksFragmentBinding.inflate(inflater, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object{

        fun newInstance(): HowExchangeWorksFragment{
            return HowExchangeWorksFragment()
        }
    }
}