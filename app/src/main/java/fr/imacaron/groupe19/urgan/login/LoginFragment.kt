package fr.imacaron.groupe19.urgan.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentLoginBinding
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.*

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

    @OptIn(DelicateCoroutinesApi::class)
    private fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        GlobalScope.launch {
            withContext(Dispatchers.IO + h) {
                if(email.isNotEmpty() && password.isNotEmpty()){
                    try{
                        FirebaseAPIManager.loginUser(email, password)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@LoginFragment.context, "Login successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginFragment.context, HomeActivity::class.java))
                        }
                    }catch (e: FirebaseNetworkException){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@LoginFragment.context, resources.getString(R.string.no_connection), Toast.LENGTH_LONG).show()
                        }
                    }catch (e: FirebaseAuthInvalidCredentialsException) {
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@LoginFragment.context, resources.getString(R.string.bad_credentials), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
