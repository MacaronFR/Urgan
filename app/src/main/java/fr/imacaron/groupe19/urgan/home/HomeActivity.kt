package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.data.User
import fr.imacaron.groupe19.urgan.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        FirebaseAPIManager.signOut()
        Toast.makeText(this, "Log Out Successful", Toast.LENGTH_LONG).show()
        this.finish()
    }

}
