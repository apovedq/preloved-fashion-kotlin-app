package com.example.firebase_config.model.entity

import android.app.ActivityManager.TaskDescription

data class PostEntity(
    var brand : String,
    var category : String,
    var description : String,
    var fashionPoints : Int,
    var gender : String,
    var id : String,
    var image : String,
    var size : String,
    var title : String,
    var userId : String
)
