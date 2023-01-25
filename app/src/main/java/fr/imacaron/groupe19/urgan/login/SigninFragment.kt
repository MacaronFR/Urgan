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
import fr.imacaron.groupe19.urgan.home.HomeActivity

class SigninFragment: Fragment() {
    private lateinit var binding: FragmentSigninBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Initialize Firebase Auth
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doSignin.setOnClickListener {
            registerUser(view);
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

        FirebaseAPIManager.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this.requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show()
                    val user = FirebaseAPIManager.auth.currentUser
                    startActivity(Intent(this.requireContext(), HomeActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this.requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
