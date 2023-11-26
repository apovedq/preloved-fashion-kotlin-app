package com.example.firebase_config.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityExchangeClothesBinding
import com.example.firebase_config.model.repository.PostRepository
import com.example.firebase_config.viewmodels.ExchangeViewModel
import com.example.firebase_config.views.fragment.exchangeClothes.HowExchangeWorksFragment
import com.example.firebase_config.views.fragment.exchangeClothes.NoClothesToExchangeFragment
import com.example.firebase_config.views.fragment.exchangeClothes.SelectClothesToExchangeFragment
import com.example.firebase_config.views.fragment.exchangeClothes.SelectedMiniProduct
import com.example.firebase_config.views.fragment.exchangeClothes.SuccessfulExchangeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExchangeClothesActivity : AppCompatActivity() {

    private val postRepository = PostRepository()

    private val binding by lazy {
        ActivityExchangeClothesBinding.inflate(layoutInflater)
    }

    val selectClothesToExchangeFragment by lazy {
        SelectClothesToExchangeFragment.newInstance()
    }

    val successfulExchangeFragment by lazy {
        SuccessfulExchangeFragment.newInstance()
    }

    private val noClothesToExchangeFragment by lazy {
        NoClothesToExchangeFragment.newInstance()
    }

    val howExchangeWorksFragment by lazy {
        HowExchangeWorksFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val hasPosts = postRepository.userHasPosts()

            withContext(Dispatchers.Main) {
                if (hasPosts) {
                    loadFragment(selectClothesToExchangeFragment)
                } else {
                    loadFragment(noClothesToExchangeFragment)
                }
            }
        }

    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}
