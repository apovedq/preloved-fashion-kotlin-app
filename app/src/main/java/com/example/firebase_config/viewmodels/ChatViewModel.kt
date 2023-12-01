package com.example.firebase_config.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase_config.model.entity.Chat
import com.example.firebase_config.model.entity.Message
import com.example.firebase_config.model.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel: ViewModel() {
    private val transactionRepository = TransactionRepository()
    private lateinit var sellerId: String
    private lateinit var buyerId: String
    private lateinit var sellerChat: Chat


    fun getChat(){
        viewModelScope.launch(Dispatchers.IO) {
            val task = transactionRepository.getChatBySellerIdAndBuyerId(sellerId, buyerId)

            task.addOnCompleteListener{ task ->
                if(task.result?.size() == 0){
                    createChat()
                }else{
                    for (document in task.result){
                        sellerChat = document.toObject(Chat::class.java)
                    }
                }
                getMessages()
            }
        }
    }

    private fun getMessages() {
        val messagesReference = transactionRepository.getMessages(sellerChat.chatId)
        messagesReference.addSnapshotListener{ value, error ->
            for (document in value!!.documents){
                val message = document.toObject(Message::class.java)
                //binding.messagesTV.append(mmesage?.message + \n\n)
            }
        }
    }

    private fun createChat() {
        val chatId = UUID.randomUUID().toString()

        sellerChat = Chat(chatId, buyerId)
        val buyerChat = Chat(chatId, sellerId)

        transactionRepository.createChat(buyerId, chatId, sellerChat)
        transactionRepository.createChat(sellerId, chatId, buyerChat)
    }

    fun setSellerId(sellerId: String){
        this.sellerId = sellerId
    }

    fun setBuyerId(buyerId: String){
        this.buyerId = buyerId
    }
}