package com.example.firebase_config.model.dto

data class Post(
    var category: String = "",
    var gender: String = "",
    var name: String = "",
    var brand: String = "",
    var size: String = "",
    var description: String = ""
)

data class ErrorPostMessage(
    var message: String = ""
)