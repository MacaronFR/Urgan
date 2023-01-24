package fr.imacaron.groupe19.urgan.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.databinding.FragmentSigninBinding
import fr.imacaron.groupe19.urgan.home.HomeActivity

class SigninFragment: Fragment() {
    private lateinit var binding: FragmentSigninBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Initialize Firebase Auth
        auth = Firebase.auth
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
        var username: String = view.findViewById<EditText>(R.id.username).text.toString()
        var email: String = view.findViewById<EditText>(R.id.email).text.toString()
        var password: String = view.findViewById<EditText>(R.id.password).text.toString()
        var check_password: String = view.findViewById<EditText>(R.id.check_password).text.toString()

        if (password != check_password) {
            view.findViewById<EditText>(R.id.check_password).setText("")
        }
        else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this.requireContext(), "Registration Successful", Toast.LENGTH_LONG).show()
                        val user = auth.currentUser
                        startActivity(Intent(this.requireContext(), HomeActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this.requireContext(), "An error occurred", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }
}
