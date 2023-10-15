package com.example.firebase_config.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.PostInformationFragmentBinding
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.viewModel.PostViewModel

class PostInformationFragment: Fragment() {

    private lateinit var binding: PostInformationFragmentBinding
    private val vm : PostViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostInformationFragmentBinding.inflate(inflater, container, false)

        binding.FPSurveyBtn.setOnClickListener {

            val post = Post()
            post.category = binding.categorySpinner.selectedItem.toString()
            post.gender = binding.genderSpinner.selectedItem.toString()
            post.name = binding.nameTxt.editableText.toString()
            post.brand = binding.brandTxt.editableText.toString()
            post.size = binding.sizeSpinner.selectedItem.toString()
            post.description= binding.descriptionTxt.editableText.toString()

            vm.createPost(post)

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.loadFragment(createPostActivity.firstQuestionSurveyFragment)
        }
        return binding.root
    }

    companion object{
        fun newInstance(): PostInformationFragment {
            return PostInformationFragment()
        }
    }
}