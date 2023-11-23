package com.example.firebase_config.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityExchangeClothesBinding
import com.example.firebase_config.views.fragment.exchangeClothes.HowExchangeWorksFragment
import com.example.firebase_config.views.fragment.exchangeClothes.NoClothesToExchangeFragment
import com.example.firebase_config.views.fragment.exchangeClothes.SelectClothesToExchangeFragment
import com.example.firebase_config.views.fragment.exchangeClothes.SuccessfulExchangeFragment

class ExchangeClothesActivity : AppCompatActivity() {

    private val binding: ActivityExchangeClothesBinding by lazy {
        ActivityExchangeClothesBinding.inflate(layoutInflater)
    }

    val selectClothesToExchangeFragment by lazy {
        SelectClothesToExchangeFragment.newInstance()
    }

    val successfulExchangeFragment by lazy {
        SuccessfulExchangeFragment.newInstance()
    }

    val noClothesToExchangeFragment by lazy {
        NoClothesToExchangeFragment.newInstance()
    }

    val howExchangeWorksFragment by lazy {
        HowExchangeWorksFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}