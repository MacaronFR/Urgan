package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

val games = listOf(
    Game("Team Fortress 2", "Valve", 0.0, "https://cdn.cloudflare.steamstatic.com/steam/apps/440/capsule_231x87.jpg?t=1665425286", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
    Game("Team Fortress 2", "Valve", 0.0, "https://google.fr", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
    Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
    Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
    Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
    Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742)
)