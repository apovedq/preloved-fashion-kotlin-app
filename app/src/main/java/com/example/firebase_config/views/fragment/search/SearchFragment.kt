package com.example.firebase_config.views.fragment.search

import PostAdapterFeed
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FeedFragmentBinding
import com.example.firebase_config.databinding.FragmentSearchBinding
import com.example.firebase_config.viewmodels.FeedViewModel
import com.example.firebase_config.viewmodels.SearchViewModel
import com.example.firebase_config.views.fragment.authentication.SignInFragment
import com.example.firebase_config.views.fragment.profile.DescriptionFragment
class SearchFragment : Fragment() {
    private var originalMode : Int? = null

    private lateinit var binding: FragmentSearchBinding

    private val postsVM: FeedViewModel by activityViewModels()
    private lateinit var searchInput : SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)


//        postsVM.downloadPosts()

        searchInput = binding.searchInput

        postsVM.feed.observe(viewLifecycleOwner){posts ->
            val adapter = PostAdapterFeed(posts)
            binding.searchRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }


        searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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


