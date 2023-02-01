package fr.imacaron.groupe19.urgan.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentSigninBinding
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SigninFragment: Fragment() {
    private lateinit var binding: FragmentSigninBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doSignin.setOnClickListener {
            registerUser(view)
        }

        binding.goLogin.setOnClickListener {
            findNavController().navigate(R.id.LoginFragment)
        }

        binding.forgot.setOnClickListener {
            findNavController().navigate(R.id.ForgotFragment)
        }
    }

    private fun registerUser(view: View) {
        val username: String = view.findViewById<EditText>(R.id.username).text.toString()
        val email: String = view.findViewById<EditText>(R.id.email).text.toString()
        val password: String = view.findViewById<EditText>(R.id.password).text.toString()
        val check_password: String = view.findViewById<EditText>(R.id.check_password).text.toString()

        if (password != check_password) {
            view.findViewById<EditText>(R.id.check_password).setText("")
        }

        GlobalScope.launch {
            withContext(Dispatchers.IO + h) {

                val isConnected = FirebaseAPIManager.signinUser(username, email, password)

                withContext(Dispatchers.Main) {
                    if (isConnected) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this@SigninFragment.context, "Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SigninFragment.context, HomeActivity::class.java)
                        )
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this@SigninFragment.context, "An error occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
