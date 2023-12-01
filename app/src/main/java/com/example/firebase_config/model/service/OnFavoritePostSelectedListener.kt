package com.example.firebase_config.model.service

import com.example.firebase_config.model.entity.MiniPost

interface OnFavoritePostSelectedListener {
    fun onPostSelected(post: MiniPost)
    fun onPostDeselected(post: MiniPost)
}