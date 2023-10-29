package com.example.firebase_config.model.entity

data class User(
    var name: String = "",
    var email: String = "",
    var userId: String = "",
    var username: String = "",
    var description: String = "",
    var exchanges: Int = 0,
    var favorite: ArrayList<String> = arrayListOf(),
    var photo: String = "",
    var rating: Int = 0,
)
