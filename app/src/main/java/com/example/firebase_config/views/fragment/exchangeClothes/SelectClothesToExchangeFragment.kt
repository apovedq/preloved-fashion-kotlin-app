package com.example.firebase_config.views.fragment.exchangeClothes

import PostAdapterFeed
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.SelectClothesToExchangeFragmentBinding
import com.example.firebase_config.viewmodels.ExchangeViewModel

class SelectClothesToExchangeFragment: Fragment() {

    private val viewModel: ExchangeViewModel by activityViewModels()
    private lateinit var binding: SelectClothesToExchangeFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectClothesToExchangeFragmentBinding.inflate(inflater, container, false)

        viewModel.downloadPosts()

        viewModel.userPosts.observe(viewLifecycleOwner){posts ->
            val adapter = PostAdapterFeed(posts)
            binding.exchangeRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        binding.fpRequiredTV.text = viewModel.getFashionPoints().toString()

        return binding.root
    }

    companion object{
        fun newInstance(): SelectClothesToExchangeFragment{
            return SelectClothesToExchangeFragment()
        }
    }
}