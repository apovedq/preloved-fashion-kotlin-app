package com.example.firebase_config.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.PostInformationFragmentBinding
import com.example.firebase_config.databinding.ThirdQuestionSurveyFragmentBinding
import com.example.firebase_config.viewModel.PostViewModel

class ThirdQuestionSurveyFragment : Fragment() {

    private lateinit var binding: ThirdQuestionSurveyFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ThirdQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.question4Btn.setOnClickListener {

            val bundle = arguments ?: Bundle()
            val progress = bundle.getInt("progress")
            val newProgress = progress+binding.seekBar3Q.progress
            bundle.putInt("progress", newProgress)
            arguments = bundle

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.fourthQuestionSurveyFragment.arguments = bundle
            createPostActivity.loadFragment(createPostActivity.fourthQuestionSurveyFragment)
        }

        return binding.root
    }

    companion object{
        fun newInstance() : ThirdQuestionSurveyFragment{
            return ThirdQuestionSurveyFragment()
        }
    }
}

