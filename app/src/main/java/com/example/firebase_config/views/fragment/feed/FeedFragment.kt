package com.example.firebase_config.views.fragment.feed

import PostAdapterFeed
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FeedFragmentBinding
import com.example.firebase_config.viewmodels.FeedViewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by activityViewModels()

    private lateinit var binding:FeedFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FeedFragmentBinding.inflate(inflater, container, false)

        viewModel.downloadPosts()

        viewModel.feed.observe(viewLifecycleOwner){posts ->
            val adapter = PostAdapterFeed(posts)
            binding.feedRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object{
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }
}