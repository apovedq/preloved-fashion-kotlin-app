package com.example.firebase_config.views.fragment.postDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.firebase_config.databinding.PostDetailFragmentBinding
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.viewmodels.FeedViewModel
import com.example.firebase_config.views.CreatePostActivity
import com.example.firebase_config.views.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostDetailsFragment: Fragment() {

    private val viewModel: FeedViewModel by activityViewModels()
    private lateinit var binding: PostDetailFragmentBinding
    private var productId:String? = null
    private var postInformation: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString("ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostDetailFragmentBinding.inflate(inflater, container, false)

        productId?.let{viewModel.findPostById(it)}

        //Asigns post info
        viewModel.postInfo.observe(viewLifecycleOwner) { post ->
            if (post != null) {
                postInformation = post
                Toast.makeText(requireContext(), postInformation.toString(), Toast.LENGTH_LONG).show()

                binding.backBTN.setOnClickListener(){
                    //Back to feed fragment
                    val backToFeedActivity = activity as HomeActivity
                    backToFeedActivity.showFragment(backToFeedActivity.feedFragmentInstance)
                }



                postInformation?.let {

                    val sizeString = it.size.split("/")[0]
                    val currentState = (it.fashionPoints/10).toString()

                    binding.titleTV.text = it.title
                    binding.brandTV.text= it.brand
                    binding.fpTV.text = "${it.fashionPoints.toString()} FP"
                    binding.descriptionTV.text = it.description
                    binding.genderTV.text = it.gender
                    binding.sizeTV.text = sizeString
                    binding.stateTV.text = "${currentState}/10"
                }
            }
        }

        //Assigns image to main
        viewModel.imageUrl.observe(viewLifecycleOwner){url ->
            if(url != null){
                Glide.with(this).load(url).into(binding.mainImageIV)
            }
        }

        return binding.root
    }
    companion object {
        fun newInstance(idProduct:String): PostDetailsFragment{
            return PostDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("ID", idProduct)
                }
            }
        }
    }
}