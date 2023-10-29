package com.example.firebase_config.views.fragment.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.firebase_config.databinding.FragmentSignInBinding
import com.example.firebase_config.viewmodels.AuthViewModel
import com.example.firebase_config.views.AuthActivity
import com.example.firebase_config.views.HomeActivity

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)


        binding.registerLink.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.loadFragment(authActivity.signupFragment)
        }

        binding.logInBTN.setOnClickListener {
            val userEmail = binding.editMailPT.text.toString()
            val pass = binding.editPasswordPT.text.toString()
            vm.signIn(userEmail, pass)
        }

        binding.forgotPassTV.setOnClickListener{
            val currentMail = binding.editMailPT.text.toString()
            vm.recoverPassword(currentMail)
        }

        vm.authStateLV.observe(viewLifecycleOwner){ state ->
            if(state.isAuth){
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}


//                val fbuser = Firebase.auth.currentUser
//
//                Toast.makeText(this, "Haz entrado exitosamente", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this, MainHomePageActivity::class.java))
//                finish()
//
//                //Checks if email is verified
//                if(fbuser!!.isEmailVerified){
//
//                    //1. Falta añadir la verificacion del usuario almacenado
//                    //2. Tambien falta salvar el usuari
//
//                } else {
//                    Toast.makeText(this, "Su correo no ha sido verificado", Toast.LENGTH_LONG).show()
//                }
//            }.addOnFailureListener{
//                //If it doesnt work
//                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//            }
//        }

        //Forgot password function
//        binding.forgotPassTV.setOnClickListener {
//
//            val currentMail = binding.editMailPT.text.toString()
//            if(currentMail != "") {
//                //Falta añadir verificacion, que el correo si este registrado en los usuarios
//
//                Firebase.auth.sendPasswordResetEmail(currentMail)
//                    .addOnSuccessListener { Toast.makeText(this, "Le hemos enviado las indicaciones para restablecer su contraseña a $currentMail", Toast.LENGTH_LONG).show()}
//                    .addOnFailureListener{  Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() }
//            } else {
//                Toast.makeText(this, "Escriba su correo en el area indicada para cambiar la contraseña", Toast.LENGTH_LONG).show()
//            }
//
//        }
