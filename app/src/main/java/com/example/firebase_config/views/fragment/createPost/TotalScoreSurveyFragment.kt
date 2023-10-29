package com.example.firebase_config.views.fragment.createPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.TotalScoreSurveyFragmentBinding
import com.example.firebase_config.viewmodels.PostViewModel

class TotalScoreSurveyFragment : Fragment() {
    private lateinit var binding: TotalScoreSurveyFragmentBinding
    private val vm : PostViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TotalScoreSurveyFragmentBinding.inflate(inflater, container, false)

        binding.scoreTV.text = vm.getFashionPoints().toString()

        binding.endBtn.setOnClickListener {
            //Pantalla de perfil!!!
        }

        return binding.root
    }

    companion object{
        fun newInstance(): TotalScoreSurveyFragment {
            return TotalScoreSurveyFragment()
        }
    }
}