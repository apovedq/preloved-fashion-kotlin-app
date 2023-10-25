package com.example.firebase_config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.ActivityCreatePostBinding
import com.example.firebase_config.fragments.FifthQuestionSurveyFragment
import com.example.firebase_config.fragments.FirstQuestionSurveyFragment
import com.example.firebase_config.fragments.FourthQuestionSurveyFragment
import com.example.firebase_config.fragments.PostInformationFragment
import com.example.firebase_config.fragments.SecondQuestionSurveyFragment
import com.example.firebase_config.fragments.ThirdQuestionSurveyFragment
import com.example.firebase_config.fragments.TotalScoreSurveyFragment

class CreatePostActivity : AppCompatActivity() {

    private val binding: ActivityCreatePostBinding by lazy {
        ActivityCreatePostBinding.inflate(layoutInflater)
    }

    val postInformationFragment by lazy{
        PostInformationFragment.newInstance()
    }
    val firstQuestionSurveyFragment by lazy {
        FirstQuestionSurveyFragment.newInstance()
    }
    val secondQuestionSurveyFragment by lazy{
        SecondQuestionSurveyFragment.newInstance()
    }
    val thirdQuestionSurveyFragment by lazy{
        ThirdQuestionSurveyFragment.newInstance()
    }
    val fourthQuestionSurveyFragment by lazy{
        FourthQuestionSurveyFragment.newInstance()
    }
    val fifthQuestionSurveyFragment by lazy{
        FifthQuestionSurveyFragment.newInstance()
    }
    val totalScoreSurveyFragment by lazy{
        TotalScoreSurveyFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(postInformationFragment)
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}