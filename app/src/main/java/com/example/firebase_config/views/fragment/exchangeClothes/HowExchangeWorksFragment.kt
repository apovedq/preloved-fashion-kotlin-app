package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.HowExchangeWorksFragmentBinding
import com.example.firebase_config.views.ExchangeClothesActivity
import com.example.firebase_config.views.HomeActivity

class HowExchangeWorksFragment: Fragment() {

    private lateinit var binding: HowExchangeWorksFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HowExchangeWorksFragmentBinding.inflate(inflater, container, false)

        binding.closeBtn.setOnClickListener {
            val exchangeClothesActivity = activity as ExchangeClothesActivity
            exchangeClothesActivity.loadFragment(exchangeClothesActivity.selectClothesToExchangeFragment)
        }

        return binding.root
    }

    companion object{

        fun newInstance(): HowExchangeWorksFragment{
            return HowExchangeWorksFragment()
        }
    }
}