package com.example.firebase_config.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityChatBinding
import com.example.firebase_config.databinding.ChatFragmentBinding
import com.example.firebase_config.model.entity.User
import com.example.firebase_config.views.fragment.Chat.ChatFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {
    private val chatFragment = ChatFragment()

    private val binding:ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(layoutInflater)
    }

    val chatFragmentInstance: ChatFragment by lazy {
        ChatFragment.newInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showFragment(chatFragment)
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                chatFragment.setSellerUser(intent.extras?.get("sellerUser") as User)
                chatFragment.setBuyerUser(intent.extras?.get("buyerUser") as User)
            }
        }
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerFeed, fragment)
            .commit()
    }
}