package com.example.firebase_config.model.dto

import android.net.Uri

data class Post (
    var postId: String = "",
    var brand: String = "",
    var category: String = "",
    var description: String = "",
    var fashionPoints: Int = 0,
    var gender: String = "",
    var image: String = "",
    var size: String = "",
    var title: String = "",
    var userId: String = ""
)