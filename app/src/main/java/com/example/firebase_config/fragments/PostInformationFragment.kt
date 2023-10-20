package com.example.firebase_config.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.CreatePostActivity
import com.example.firebase_config.databinding.PostInformationFragmentBinding
import com.example.firebase_config.model.dto.Post
import com.example.firebase_config.viewModel.PostViewModel

class PostInformationFragment: Fragment() {

    private lateinit var binding: PostInformationFragmentBinding
    private val vm : PostViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostInformationFragmentBinding.inflate(inflater, container, false)

        val garmentGender = listOf(
            "Femenino",
            "Masculino",
            "Unisex"
        )

        val garmentCategory = listOf(
            "Camisas",
            "Pantalones",
            "Tops",
            "Zapatos",
            "Shorts",
            "Vestidos"
        )

        val topGarmentSizes = listOf(
            "XXXS / 0-2 US / 30 EU",
            "XXS / 4 US / 32 EU",
            "XS/ 6 US /34 EU",
            "S/ 8 US / 36 EU",
            "M/ 10 US / 38 EU",
            "L/ 12 US / 40 EU",
            "XL/ 14 US / 42 EU",
            "2XL / 16 US / 44 EU",
            "3XL / 18 US / 46 EU",
            "4XL / 18 US / 48 EU",
            "5XL / 22 US / 50 EU",
            "6XL / 24 US / 52 EU",
            "Única"
        )

        val lowerGarmentSizes = listOf(
            "XXXS / 0 / 30 EU",
            "XXS / 2 US / 32 EU",
            "XS / 4-6 US / 34 EU",
            "S / 6-8 US / 36 EU",
            "M / 8-10 US / 38 EU",
            "L / 10-12 US / 40 EU",
            "XL / 12-14 US / 42 EU",
            "2XL / 14-16 US / 44 EU",
            "3XL / 16 US / 46 EU",
            "4XL/ 18 US / 48 EU",
            "5XL / 20 US / 50 EU",
            "6XL / 22 US / 52 EU",
            "Única"
        )

        val shoeSizes = listOf(
            "33,5 / 5 US",
            "34 / 5 US / 36 EU",
            "34,5 / 5.5 US / 36.5 EU",
            "35 / 6 US / 37 EU",
            "36 / 6.5 US / 37.5 EU",
            "37 / 7.5 US / 38.5 EU",
            "37,5 / 8 US /39 EU",
            "38 / 8.5 US / 39 EU",
            "39 / 9 US / 40 EU",
            "39,5 / 9.5 / 40.5 EU",
            "40 / 10 US / 41 EU",
            "40,5 / 10.5 US / 41.5 EU",
            "41 / 11 US / 42 EU",
            "42 / 11 US /  42.5-43 EU",
            "42.5 / 11.5 US / 43.5 EU",
            "43 / 12 US / 44 EU"
        )

        val garmentGenderAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, garmentGender)
        val garmentCategoryAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, garmentCategory)
        val topGarmentSizesAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, topGarmentSizes)
        val lowerGarmentSizesAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, lowerGarmentSizes)
        val shoesSizesAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, shoeSizes)

        binding.genderSpinner.adapter = garmentGenderAdapter
        binding.categorySpinner.adapter = garmentCategoryAdapter

        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position).toString()
                when (selectedCategory) {
                    "Camisas", "Tops", "Vestidos" -> {
                        binding.sizeSpinner.adapter = topGarmentSizesAdapter
                    }
                    "Pantalones", "Shorts" -> {
                        binding.sizeSpinner.adapter = lowerGarmentSizesAdapter
                    }
                    "Zapatos" -> {
                        binding.sizeSpinner.adapter = shoesSizesAdapter
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed here, but you can add code if necessary.
            }
        }

        binding.FPSurveyBtn.setOnClickListener {

            val post = Post()
            post.category = binding.categorySpinner.selectedItem.toString()
            post.gender = binding.genderSpinner.selectedItem.toString()
            post.name = binding.nameTxt.editableText.toString()
            post.brand = binding.brandTxt.editableText.toString()
            post.size = binding.sizeSpinner.selectedItem.toString()
            post.description= binding.descriptionTxt.editableText.toString()

            vm.createPost(post)

            val createPostActivity = activity as CreatePostActivity
            createPostActivity.loadFragment(createPostActivity.firstQuestionSurveyFragment)
        }
        return binding.root
    }

    companion object{
        fun newInstance(): PostInformationFragment {
            return PostInformationFragment()
        }
    }
}