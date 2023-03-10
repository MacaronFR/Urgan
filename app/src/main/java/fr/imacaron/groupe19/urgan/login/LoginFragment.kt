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

        binding.spinnerLoading.root.visibility = View.VISIBLE
        binding.spinnerLoading.root.bringToFront()

        GlobalScope.launch {
            withContext(Dispatchers.IO + h) {
                if(email.isNotEmpty() && password.isNotEmpty()){
                    try{
                        val isConnected = FirebaseAPIManager.loginUser(email, password)
                        withContext(Dispatchers.Main) {
                            if (isConnected) {
                                Toast.makeText(this@LoginFragment.context, "Login successful", Toast.LENGTH_SHORT).show()
                                binding.spinnerLoading.root.visibility = View.GONE
                                startActivity(Intent(this@LoginFragment.context, HomeActivity::class.java))
                            } else {
                                Toast.makeText(this@LoginFragment.context, "Unable to login. Check your input or try again later", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }catch (e: FirebaseNetworkException){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@LoginFragment.context, "Pas de connexion internet", Toast.LENGTH_LONG).show()
                        }
                    }catch (e: FirebaseAuthInvalidCredentialsException) {
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@LoginFragment.context, "Les informations renseign?? sont incorrecte", Toast.LENGTH_SHORT).show()
                        }
                    }
                    withContext(Dispatchers.Main){
                        binding.spinnerLoading.root.visibility = View.GONE
                    }
                }
            }
        }
    }
}
