package com.example.firebase_config.model.entity

data class Message (
    var messageId: String = "",
    var transactionId: String = "",
    var userId: String = "",
    var text: String = "",
    var date: Long = 0
)