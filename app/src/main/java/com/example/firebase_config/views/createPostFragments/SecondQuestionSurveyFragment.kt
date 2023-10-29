package com.example.firebase_config.views.createPostFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.views.CreatePostActivity
import com.example.firebase_config.databinding.SecondQuestionSurveyFragmentBinding

class SecondQuestionSurveyFragment : Fragment() {
    private lateinit var binding: SecondQuestionSurveyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondQuestionSurveyFragmentBinding.inflate(inflater, container, false)

        binding.question3Btn.setOnClickListener {

            val bundle = arguments ?: Bundle()
            val progress = bundle.getInt("progress")
            val newProgress = progress+(binding.seekBar2Q.progress+1)
            bundle.putInt("progress", newProgress)
            arguments = bundle

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.thirdQuestionSurveyFragment.arguments = bundle
            createPostActivity.loadFragment(createPostActivity.thirdQuestionSurveyFragment)
        }

        return binding.root
    }

    companion object{
        fun newInstance(): SecondQuestionSurveyFragment {
            return SecondQuestionSurveyFragment()
        }
    }

}