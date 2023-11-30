package com.example.firebase_config.views.fragment.exchangeClothes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.R
import com.example.firebase_config.databinding.SelectClothesToExchangeFragmentBinding
import com.example.firebase_config.model.service.PostAdapterExchange
import com.example.firebase_config.viewmodels.ExchangeViewModel
import com.example.firebase_config.views.ExchangeClothesActivity
import com.example.firebase_config.views.HomeActivity

class SelectClothesToExchangeFragment: Fragment() {

    private val viewModel: ExchangeViewModel by activityViewModels()
    private lateinit var binding: SelectClothesToExchangeFragmentBinding
    private lateinit var adapter: PostAdapterExchange
    private var id: String = ""
    private var fashionPointsNeeded: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectClothesToExchangeFragmentBinding.inflate(inflater, container, false)

        viewModel.downloadPosts()
        val invalidExchangeId = resources.getIdentifier("invalid_exchange", "drawable", requireContext().packageName)
        val validExchangeId = resources.getIdentifier("valid_exchange", "drawable", requireContext().packageName)

        viewModel.userPosts.observe(viewLifecycleOwner){posts ->
            adapter = PostAdapterExchange(viewModel)
            adapter.addPosts(posts)
            binding.exchangeRecyclerView.adapter = adapter
        }

        viewModel.fashionPoints.observe(viewLifecycleOwner){
            val fashionPointsToExchange = it

            binding.nextBtn.isEnabled = false
            binding.validationIV.setImageResource(invalidExchangeId)

            if(hasEnoughtFashionPoints(fashionPointsNeeded, fashionPointsToExchange)){
                binding.nextBtn.isEnabled = true
                binding.validationIV.setImageResource(validExchangeId)
            }
        }

        viewModel.getPostById(id)
        viewModel.postToExchange.observe(viewLifecycleOwner){
            fashionPointsNeeded = it.fashionPoints
            binding.fpRequiredTV.text = it.fashionPoints.toString()
        }

        binding.exchangeInfoBtn.setOnClickListener{
            val exchangeClothesActivity = activity as ExchangeClothesActivity
            exchangeClothesActivity.loadFragment(exchangeClothesActivity.howExchangeWorksFragment)
        }

        binding.backFeedBtn.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.nextBtn.setOnClickListener {
            val successfulExchangeFragment = SuccessfulExchangeFragment()
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, successfulExchangeFragment)
            transaction.commit()
        }

        return binding.root
    }

    private fun hasEnoughtFashionPoints(fashionPointsNeeded: Int, fashionPointsToExchange: Int): Boolean {
        return (fashionPointsNeeded - 20) <= fashionPointsToExchange && (fashionPointsNeeded + 20) >= fashionPointsToExchange
    }

    fun setCurrentPostToExchangeId(id: String){
        this.id = id
    }

    companion object{
        fun newInstance(): SelectClothesToExchangeFragment{
            return SelectClothesToExchangeFragment()
        }
    }
}