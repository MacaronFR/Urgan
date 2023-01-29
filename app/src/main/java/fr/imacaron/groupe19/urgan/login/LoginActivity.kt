package fr.imacaron.groupe19.urgan.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.databinding.ActivityLoginBinding
import fr.imacaron.groupe19.urgan.home.HomeActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Channel bonjour"
            val descriptionTest = "Bah voila notif"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Bonjour", name, importance).apply {
                description = descriptionTest
            }

            val notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = FirebaseAPIManager.getCurrentUser()
        if(currentUser != null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
