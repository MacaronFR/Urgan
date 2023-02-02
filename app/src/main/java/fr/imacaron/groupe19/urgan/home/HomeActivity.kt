package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import fr.imacaron.groupe19.urgan.R
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
        if(supportFragmentManager.findFragmentById(R.id.home_fragment_content)!!.childFragmentManager.fragments[0] is HomeFragment){
            if(confirm){
                FirebaseAPIManager.signOut()
                Toast.makeText(this, resources.getString(R.string.disconnected), Toast.LENGTH_LONG).show()
                this.finish()
            }else{
                Toast.makeText(this, resources.getString(R.string.confirm_logout), Toast.LENGTH_SHORT).show()
                confirm = true
                GlobalScope.launch {
                    delay(5000)
                    confirm = false
                }
            }
        }else{
            findNavController(R.id.home_fragment_content).navigateUp()
        }
    }

}
