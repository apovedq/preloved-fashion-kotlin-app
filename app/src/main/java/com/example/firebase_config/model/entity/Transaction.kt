package com.example.firebase_config.model.entity

data class Transaction (
    var transactionId: String = "",
    var buyerId: String = "",
    var buyerPost: List<String> = mutableListOf(),
    var sellerId: String = "",
    var sellerPost: String = "",
    var state: String = ""
)