package com.example.firebase_config.model.entity

data class MiniPost (
    var image: String = "",
    var title: String = "",
    var fashionPoints: Int = 0,
    var postId: String = "",
    var isSelected: Boolean = false,
    var isAfavorite: Boolean = false,
    var category: String = ""
)