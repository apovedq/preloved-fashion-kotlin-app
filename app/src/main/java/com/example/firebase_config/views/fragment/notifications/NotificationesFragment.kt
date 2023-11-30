package com.example.firebase_config.views.fragment.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.example.firebase_config.databinding.FragmentNotificationsBinding
import com.example.firebase_config.model.service.NotificationsPagerAdapter
import com.example.firebase_config.R

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NotificationsPagerAdapter(this)
        binding.viewPagerNotifications.adapter = adapter

        TabLayoutMediator(binding.tabsNotifications, binding.viewPagerNotifications) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.intercambios)
                1 -> getString(R.string.notificaciones)
                else -> throw IllegalStateException("Unexpected position $position")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
