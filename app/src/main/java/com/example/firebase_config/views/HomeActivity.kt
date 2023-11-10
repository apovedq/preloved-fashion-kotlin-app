package com.example.firebase_config.views

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityHomeBinding
import com.example.firebase_config.databinding.MiniProductBinding
import com.example.firebase_config.views.fragment.createPost.PostInformationFragment
import com.example.firebase_config.views.fragment.feed.FeedFragment
import com.example.firebase_config.views.fragment.postDetails.PostDetailsFragment


class HomeActivity : AppCompatActivity() {

    private val feedFragment = FeedFragment()
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val miniProductBinding: MiniProductBinding by lazy{
        MiniProductBinding.inflate(layoutInflater)
    }

    val feedFragmentInstance: FeedFragment by lazy{
        FeedFragment.newInstance()
    }



    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerFeed, fragment)
            .commit()
    }

    fun showProductDeatil(id:String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerFeed, PostDetailsFragment.newInstance(id))
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showFragment(feedFragment)

        // Set the fragmentContainerFeed layout.
        binding.fragmentContainerFeed.id = R.id.fragmentContainerFeed

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}