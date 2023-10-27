package com.example.firebase_config.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.FirstQuestionSurveyFragmentBinding
import com.example.firebase_config.viewModel.PostViewModel

class FirstQuestionSurveyFragment : Fragment() {
    private lateinit var binding: FirstQuestionSurveyFragmentBinding
    private val vm : PostViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.question2Btn.setOnClickListener {
            val createPostActivity = activity as CreatePostActivity
            createPostActivity.loadFragment(createPostActivity.secondQuestionSurveyFragment)
        }

        return binding.root
    }

    companion object{
        fun newInstance(): FirstQuestionSurveyFragment {
            return FirstQuestionSurveyFragment()
        }
    }
}