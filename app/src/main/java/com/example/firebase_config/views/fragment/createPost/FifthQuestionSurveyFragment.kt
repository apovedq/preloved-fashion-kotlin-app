package com.example.firebase_config.views.fragment.createPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.firebase_config.views.CreatePostActivity
import com.example.firebase_config.databinding.FifthQuestionSurveyFragmentBinding
import com.example.firebase_config.viewmodels.PostViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

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
            val totalProgress = progress+binding.seekBar5Q.progress+1
            vm.calculateScore(totalProgress)

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.loadFragment(createPostActivity.totalScoreSurveyFragment)
        }




        return binding.root
    }

    companion object{
        fun newInstance(): FifthQuestionSurveyFragment {
            return FifthQuestionSurveyFragment()
        }
    }

}