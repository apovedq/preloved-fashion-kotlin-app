package com.example.firebase_config.model.entity

data class MiniPost (
    var image: String,
    var title: String,
    var fashionPoints: Int
){
    constructor() : this("", "", 0)
}