package com.example.firebase_config.views.fragment.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.MiniProductBinding
import com.example.firebase_config.views.CreatePostActivity
import com.example.firebase_config.views.HomeActivity
import com.example.firebase_config.views.fragment.postDetails.PostDetailsFragment

class MiniProduct : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MiniProductBinding = MiniProductBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        fun newInstance(): MiniProduct {
            return MiniProduct()
        }
    }
}