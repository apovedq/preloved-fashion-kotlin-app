package com.example.firebase_config.views


import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firebase_config.R
import com.example.firebase_config.databinding.ActivityCreatePostBinding
import com.example.firebase_config.views.createPostFragments.FifthQuestionSurveyFragment
import com.example.firebase_config.views.createPostFragments.FirstQuestionSurveyFragment
import com.example.firebase_config.views.createPostFragments.FourthQuestionSurveyFragment
import com.example.firebase_config.views.createPostFragments.PostInformationFragment
import com.example.firebase_config.views.createPostFragments.SecondQuestionSurveyFragment
import com.example.firebase_config.views.createPostFragments.ThirdQuestionSurveyFragment
import com.example.firebase_config.views.createPostFragments.TotalScoreSurveyFragment

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

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ), 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            var allGrant = true
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) allGrant = false
            }

            if (allGrant) {
                loadFragment(postInformationFragment)
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle("Aceptar permisos")
                    setMessage("Debe aceptar los permisos solicitados para poder continuar, lo dirigiremos a ajustes para que los actualice.")
                    setPositiveButton("Ok") { _, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.fromParts("package", packageName, null)
                        startActivity(intent)
                        loadFragment(postInformationFragment)
                    }
                }.show()
            }

        }
    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}