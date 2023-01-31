package fr.imacaron.groupe19.urgan.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentLoginBinding
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signin.setOnClickListener {
            findNavController().navigate(R.id.SigninFragment)
        }

        binding.forgot.setOnClickListener {
            findNavController().navigate(R.id.ForgotFragment)
        }

        binding.login.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                if(email.isNotEmpty() && password.isNotEmpty()){
                    val isConnected = FirebaseAPIManager.loginUser(email, password)
                    withContext(Dispatchers.Main) {
                        if (isConnected) {
                            Toast.makeText(this@LoginFragment.context, "Login successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginFragment.context, HomeActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this@LoginFragment.context, "Unable to login. Check your input or try again later", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }
}
