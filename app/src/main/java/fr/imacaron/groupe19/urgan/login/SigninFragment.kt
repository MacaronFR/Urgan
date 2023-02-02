package fr.imacaron.groupe19.urgan.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentSigninBinding
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.*

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
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun registerUser(view: View) {

        binding.spinnerLoading.root.visibility = View.VISIBLE
        binding.spinnerLoading.root.bringToFront()

        val username: String = view.findViewById<EditText>(R.id.username).text.toString()
        val email: String = view.findViewById<EditText>(R.id.email).text.toString()
        val password: String = view.findViewById<EditText>(R.id.password).text.toString()
        val checkPassword: String = view.findViewById<EditText>(R.id.check_password).text.toString()

        if (password != checkPassword) {
            view.findViewById<EditText>(R.id.check_password).setText("")
        }

        GlobalScope.launch {
            withContext(Dispatchers.IO + h) {

                val isConnected = FirebaseAPIManager.signinUser(username, email, password)

                withContext(Dispatchers.Main) {
                    binding.spinnerLoading.root.visibility = View.GONE
                    if (isConnected) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this@SigninFragment.context, resources.getString(R.string.registration_ok), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SigninFragment.context, HomeActivity::class.java)
                        )
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this@SigninFragment.context, resources.getString(R.string.registration_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
