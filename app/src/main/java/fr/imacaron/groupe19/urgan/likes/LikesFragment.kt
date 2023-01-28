package fr.imacaron.groupe19.urgan.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentLikeBinding
import fr.imacaron.groupe19.urgan.home.games
import fr.imacaron.groupe19.urgan.list.GameAdapter

class LikesFragment: Fragment() {
    private lateinit var binding: FragmentLikeBinding

    private lateinit var likesGames: List<Game>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        likesGames = games
        binding.list.adapter = GameAdapter(listOf()){
            findNavController().navigate(R.id.DetailFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
