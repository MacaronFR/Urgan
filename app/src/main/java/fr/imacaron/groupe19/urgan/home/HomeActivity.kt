package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

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

val games = listOf(
    Game("Team Fortress 2", "Valve", "-€", "https://cdn.cloudflare.steamstatic.com/steam/apps/440/capsule_231x87.jpg?t=1665425286", "", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "https://cdn.cloudflare.steamstatic.com/steam/apps/440/page_bg_generated_v6b.jpg?t=1665425286", false, true, arrayListOf(), 742),
    Game("Team Fortress 2", "Valve", "-€", "https://google.fr", "",  "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, arrayListOf(), 742),
    Game("Team Fortress 2", "Valve", "-€", "picture", "", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, arrayListOf(), 742),
    Game("Team Fortress 2", "Valve", "-€", "picture", "", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, arrayListOf(), 742),
    Game("Team Fortress 2", "Valve", "-€", "picture", "", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, arrayListOf(), 742),
    Game("Team Fortress 2", "Valve", "-€", "picture", "", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, arrayListOf(), 742)
)