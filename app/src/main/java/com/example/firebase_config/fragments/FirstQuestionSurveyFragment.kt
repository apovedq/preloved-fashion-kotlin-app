package com.example.firebase_config.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.FirstQuestionSurveyFragmentBinding

class FirstQuestionSurveyFragment : Fragment() {
    private lateinit var binding: FirstQuestionSurveyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.question2Btn.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("progress", binding.seekBar1Q.progress+1)
            arguments = bundle

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.secondQuestionSurveyFragment.arguments = bundle
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