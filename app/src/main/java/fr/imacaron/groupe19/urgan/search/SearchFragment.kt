package fr.imacaron.groupe19.urgan.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentSearchBinding
import fr.imacaron.groupe19.urgan.list.GameAdapter

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val games = listOf(
        Game("Team Fortress 2", "Valve", 0.0, "https://cdn.cloudflare.steamstatic.com/steam/apps/440/capsule_231x87.jpg?t=1665425286", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
        Game("Team Fortress 2", "Valve", 0.0, "https://google.fr", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
        Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
        Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
        Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742),
        Game("Team Fortress 2", "Valve", 0.0, "picture", "FPS Free-to-play et Free-to-win avec des micros transactions pour les cosmétiques", "banner", false, true, listOf(), 742)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val adapter = GameAdapter(games)

        binding.list.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.requestFocus()
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
