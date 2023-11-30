package com.example.firebase_config.model.service

import com.example.firebase_config.views.fragment.notifications.NotificationsFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firebase_config.views.fragment.notifications.IntercambiosFragment

class NotificationsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val TAB_COUNT = 2

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IntercambiosFragment() // Reemplaza por tu fragmento de intercambios
            1 ->  NotificationsFragment()      // Reemplaza por tu fragmento de chat
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
