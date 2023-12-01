package com.example.firebase_config.views.fragment.profile

import PostAdapterFeed
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.firebase_config.databinding.ProfileFragmentBinding
import com.example.firebase_config.viewmodels.ProfileViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.HomeActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    val vm:ProfileViewModel by activityViewModels()
    val currentUser = Firebase.auth.currentUser

    //Launcher for gallery
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

    //Make Gallery open and get image ref
    fun onGalleryResult(result: ActivityResult){
        val uri = result.data?.data
        Glide.with(this).load(uri).into(binding.profileImg)
        uri?.let {
            vm.uploadImage(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)

        //Show information
        vm.showUser()

        //Using info from vm, show and change on profile page
        vm.userLD.observe(viewLifecycleOwner){
            //Image
            Glide.with(this).load(it.photo).into(binding.profileImg)


            if(currentUser != null){
                //Text
                binding.usernameTxt.text = it.username
                binding.tradeNumTxt.text = it.exchanges.toString()
                binding.desTxt.text = it.description
                binding.ratingTxt.text = it.rating.toString()
            }
        }

        //Open Gallery when clicking on image
        binding.profileImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT);
            intent.type = "image/*"
            launcher.launch(intent)
        }

        //Edit button for the description text
        binding.editDesBtn.setOnClickListener {
            val homeActivity = activity as HomeActivity
            homeActivity.loadFragment(homeActivity.setDescriptionFragment)
        }

        // Initialize RecyclerView and set layout manager
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Show the posts

        binding.myProdBtn.setOnClickListener {
            currentUser?.let {
                vm.getPosts(it.uid)

                vm.myposts.observe(viewLifecycleOwner){posts ->
                    val adapter = PostAdapterFeed(vm, posts)
                    binding.postsRecyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }

        binding.myFavBtn.setOnClickListener {
            currentUser?.let {
                vm.getFavPosts(it.uid)

                vm.myposts.observe(viewLifecycleOwner){posts ->
                    val adapter = PostAdapterFeed(vm, posts)
                    binding.postsRecyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }

        binding.logOutBtn.setOnClickListener {
            vm.signOut()
            startActivity(Intent(requireContext(), AuthActivity::class.java))
            Toast.makeText(requireContext(), "Haz cerrado sesión", Toast.LENGTH_SHORT)
                .show()
        }


        return binding.root
    }

    companion object {
        fun newInstance():ProfileFragment {
            return ProfileFragment()
        }
    }
}
