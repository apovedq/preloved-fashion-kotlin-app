package com.example.firebase_config.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.FifthQuestionSurveyFragmentBinding
import com.example.firebase_config.viewModel.PostViewModel

class FifthQuestionSurveyFragment : Fragment() {
    private lateinit var binding: FifthQuestionSurveyFragmentBinding
    private val vm : PostViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FifthQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.calculateFPsBtn.setOnClickListener {

            val bundle = arguments ?: Bundle()
            val progress = bundle.getInt("progress")
            val totalProgress = progress+binding.seekBar5Q.progress
            vm.calculateScore(totalProgress)

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.loadFragment(createPostActivity.totalScoreSurveyFragment)
        }
        return binding.root
    }

    companion object{
        fun newInstance(): FifthQuestionSurveyFragment{
            return FifthQuestionSurveyFragment()
        }
    }

}