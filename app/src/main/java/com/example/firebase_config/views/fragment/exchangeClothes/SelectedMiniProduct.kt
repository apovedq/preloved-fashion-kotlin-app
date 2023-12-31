package com.example.firebase_config.views.fragment.exchangeClothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebase_config.databinding.SelectedMiniProductBinding

class SelectedMiniProduct : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SelectedMiniProductBinding = SelectedMiniProductBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object{
        fun newInstance(): SelectedMiniProduct{
            return SelectedMiniProduct()
        }
    }
}