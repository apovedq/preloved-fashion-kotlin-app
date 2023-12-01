package com.example.firebase_config.views.fragment.Chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.ChatFragmentBinding
import com.example.firebase_config.model.entity.User
import com.example.firebase_config.viewmodels.ChatViewModel
import com.example.firebase_config.views.ChatActivity

class ChatFragment : Fragment() {
    private val viewModel: ChatViewModel by activityViewModels()
    private lateinit var binding: ChatFragmentBinding

    private var sellerUser: User = User()
    private var buyerUser: User = User()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatFragmentBinding.inflate(inflater, container, false)

        viewModel.setSellerId(sellerUser.userId)
        viewModel.setBuyerId(buyerUser.userId)
        viewModel.getChat()

        return binding.root
    }

    fun setSellerUser(sellerUser: User){
        this.sellerUser = sellerUser
    }

    fun setBuyerUser(buyerUser: User){
        this.buyerUser = buyerUser
    }

    companion object {
        fun newInstance(): ChatFragment{
            return ChatFragment()
        }
    }
}