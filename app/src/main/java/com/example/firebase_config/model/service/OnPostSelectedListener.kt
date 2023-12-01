package com.example.firebase_config.model.service

import com.example.firebase_config.model.entity.MiniPost

interface OnPostSelectedListener {
    fun onPostSelected(posts: List<MiniPost>)
}