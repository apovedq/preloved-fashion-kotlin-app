package com.example.firebase_config.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.FourthQuestionSurveyFragmentBinding
import com.example.firebase_config.databinding.PostInformationFragmentBinding
import com.example.firebase_config.viewModel.PostViewModel

class FourthQuestionSurveyFragment : Fragment() {
    private lateinit var binding: FourthQuestionSurveyFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FourthQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.question5Btn.setOnClickListener {

            val bundle = arguments ?: Bundle()
            val progress = bundle.getInt("progress")
            val newProgress = progress+binding.seekBar4Q.progress
            bundle.putInt("progress", newProgress)
            arguments = bundle

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.fifthQuestionSurveyFragment.arguments = bundle
            createPostActivity.loadFragment(createPostActivity.fifthQuestionSurveyFragment)
        }
        return binding.root
    }

    companion object{
        fun newInstance(): FourthQuestionSurveyFragment{
            return FourthQuestionSurveyFragment()
        }
    }
}