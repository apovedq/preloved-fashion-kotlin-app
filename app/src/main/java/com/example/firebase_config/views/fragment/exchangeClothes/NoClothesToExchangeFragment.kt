package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.NoClothesToExchangeFragmentBinding
import com.example.firebase_config.views.ExchangeClothesActivity
import com.example.firebase_config.views.HomeActivity

class NoClothesToExchangeFragment: Fragment() {

    private lateinit var binding: NoClothesToExchangeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NoClothesToExchangeFragmentBinding.inflate(inflater, container,false)

        binding.backBtn.setOnClickListener {
            val homeActivity = activity as HomeActivity
            homeActivity.loadFragment(homeActivity.feedFragmentInstance)
        }

        return binding.root
    }

    companion object{
        fun newInstance(): NoClothesToExchangeFragment{
            return NoClothesToExchangeFragment()
        }
    }

}