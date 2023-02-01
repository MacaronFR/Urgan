package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.data.User
import fr.imacaron.groupe19.urgan.databinding.ActivityHomeBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    lateinit var user: User

    private var confirm: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBackPressed(){
        if(confirm){
            FirebaseAPIManager.signOut()
            Toast.makeText(this, "Log Out Successful", Toast.LENGTH_LONG).show()
            this.finish()
        }else{
            Toast.makeText(this, "Appuyer à nouveau pour vous déconnecter", Toast.LENGTH_SHORT).show()
            confirm = true
            GlobalScope.launch {
                delay(5000)
                confirm = false
            }
        }
    }

}
