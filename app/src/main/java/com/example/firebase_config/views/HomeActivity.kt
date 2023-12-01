package com.example.firebase_config.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityHomeBinding
import com.example.firebase_config.databinding.MiniProductBinding
import com.example.firebase_config.views.fragment.Notifications.NotificationsFragment
import com.example.firebase_config.views.fragment.feed.FeedFragment
import com.example.firebase_config.views.fragment.PostDetailsFragment.PostDetailsFragment
import com.example.firebase_config.views.fragment.profile.DescriptionFragment
import com.example.firebase_config.views.fragment.profile.ProfileFragment
import com.example.firebase_config.views.fragment.search.SearchFragment

class HomeActivity : AppCompatActivity() {

    private val feedFragment = FeedFragment()

    val profileFragment by lazy {
        ProfileFragment.newInstance()
    }

    val setDescriptionFragment by lazy {
        DescriptionFragment.newInstance()
    }

    private val binding:ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val miniProductBinding: MiniProductBinding by lazy{
        MiniProductBinding.inflate(layoutInflater)
    }

    val feedFragmentInstance: FeedFragment by lazy{
        FeedFragment.newInstance()
    }

    val searchFragment: SearchFragment by lazy{
        SearchFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showFragment(feedFragment)

        //Switch to other screens
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    showFragment(feedFragment)
                    true
                }
                R.id.navigation_profile -> {
                    showFragment(profileFragment)
                    true
                }
                R.id.navigation_create_post -> {
                    startActivity(Intent(this, CreatePostActivity::class.java))
                    true
                }
                R.id.navigation_search -> {
                    showFragment(searchFragment)
                    true
                }
                R.id.navigation_notification -> { // Asegúrate de que este ID coincida con el ítem de notificaciones en tu menu.xml
                    val notificationsFragment = NotificationsFragment()
                    showFragment(notificationsFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerFeed, fragment)
            .commit()
    }

    fun showProductDetail(id:String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerFeed, PostDetailsFragment.newInstance(id))
            .commit()
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerFeed, fragment)
            .addToBackStack(null) // Add to back stack if needed
            .commit()
    }

}