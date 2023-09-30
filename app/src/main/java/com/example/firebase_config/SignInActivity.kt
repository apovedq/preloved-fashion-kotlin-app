package com.example.firebase_config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase_config.databinding.ActivitySignInBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.logInBTN.setOnClickListener {
            val userEmail = binding.editMailPT.text.toString()
            val pass = binding.editPasswordPT.text.toString()

            Firebase.auth.signInWithEmailAndPassword(userEmail, pass).addOnSuccessListener {
                //If everything goes good do
                val fbuser = Firebase.auth.currentUser

                Toast.makeText(this, "Haz entrado exitosamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainHomePageActivity::class.java))
                finish()

                //Checks if email is verified
                if(fbuser!!.isEmailVerified){

                    //1. Falta añadir la verificacion del usuario almacenado
                    //2. Tambien falta salvar el usuari

                } else {
                    Toast.makeText(this, "Su correo no ha sido verificado", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener{
                //If it doesnt work
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}