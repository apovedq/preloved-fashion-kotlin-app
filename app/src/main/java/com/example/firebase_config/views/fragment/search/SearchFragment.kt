package com.example.firebase_config.views.fragment.search

import PostAdapterFeed
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSearchBinding
import com.example.firebase_config.viewmodels.FeedViewModel

class SearchFragment : Fragment() {
    
    private lateinit var binding: FragmentSearchBinding

    private val postsVM: FeedViewModel by activityViewModels()
    private lateinit var search : SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        postsVM.feed.observe(viewLifecycleOwner){posts ->
            val adapter = PostAdapterFeed(posts)
            binding.searchRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        search = binding.searchInput
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                postsVM.searchPosts(newText.toString())
                return true
            }

        })

        return binding.root
    }




    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

}


