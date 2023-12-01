package com.example.firebase_config.model.repository

import com.example.firebase_config.model.entity.Chat
import com.example.firebase_config.model.repository.UserRepository.Companion.GET_ALL_USERS
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TransactionRepository {
    companion object{
        const val GET_ALL_CHATS = "chats"
        const val GET_ALL_MESSAGES = "messages"
    }

    fun getChatBySellerIdAndBuyerId(sellerId: String, buyerId: String): Task<QuerySnapshot> {
        return Firebase.firestore.collection(GET_ALL_USERS).document(sellerId).collection(GET_ALL_CHATS)
            .whereEqualTo("buyerId", buyerId).get()
    }

    fun createChat(userId: String, chatId: String, chat: Chat) {
        Firebase.firestore.collection(GET_ALL_USERS).document(userId)
            .collection(GET_ALL_CHATS).document(chatId).set(chat)
    }

    fun getMessages(chatId: String): CollectionReference {
        return Firebase.firestore.collection(GET_ALL_CHATS)
            .document(chatId).collection(GET_ALL_MESSAGES)
    }
}