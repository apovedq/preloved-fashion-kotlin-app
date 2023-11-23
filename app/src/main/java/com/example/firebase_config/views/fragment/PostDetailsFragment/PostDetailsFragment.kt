package com.example.firebase_config.views.fragment.PostDetailsFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.firebase_config.databinding.PostDetailFragmentBinding
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.viewmodels.ExchangeViewModel
import com.example.firebase_config.viewmodels.FeedViewModel
import com.example.firebase_config.views.ExchangeClothesActivity
import com.example.firebase_config.views.HomeActivity

class PostDetailsFragment: Fragment() {

    private val feedVM: FeedViewModel by activityViewModels()
    private val exchangeVM: ExchangeViewModel by activityViewModels()
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

        productId?.let{feedVM.findPostById(it)}

        //Asigns post info
        feedVM.postInfo.observe(viewLifecycleOwner) { post ->
            if (post != null) {
                postInformation = post
                Toast.makeText(requireContext(), postInformation.toString(), Toast.LENGTH_LONG).show()

                binding.backBTN.setOnClickListener(){
                    //Back to feed fragment
                    val backToFeedActivity = activity as HomeActivity
                    backToFeedActivity.showFragment(backToFeedActivity.feedFragmentInstance)
                }

                postInformation?.let { current ->

                    val sizeString = current.size.split("/")[0]
                    val currentState = (current.fashionPoints/10).toString()

                    binding.titleTV.text = current.title
                    binding.brandTV.text= current.brand
                    binding.fpTV.text = "${current.fashionPoints.toString()} FP"
                    binding.descriptionTV.text = current.description
                    binding.genderTV.text = current.gender
                    binding.sizeTV.text = sizeString
                    binding.stateTV.text = "${currentState}/10"

                    binding.exchangeBtn.setOnClickListener {
                        exchangeVM.sendPostToExchangeInfo(current)
                        startActivity(Intent(requireActivity(), ExchangeClothesActivity::class.java))
                    }
                }

            }
        }

        //Assigns image to main
        feedVM.imageUrl.observe(viewLifecycleOwner){url ->
            if(url != null){
                Glide.with(this).load(url).into(binding.mainImageIV)
            }
        }

        return binding.root
    }
    companion object {
        fun newInstance(idProduct:String): PostDetailsFragment {
            return PostDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("ID", idProduct)
                }
            }
        }
    }
}