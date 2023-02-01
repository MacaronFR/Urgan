package fr.imacaron.groupe19.urgan.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.data.User
import fr.imacaron.groupe19.urgan.databinding.FragmentLikeBinding
import fr.imacaron.groupe19.urgan.error.NetworkException
import fr.imacaron.groupe19.urgan.home.HomeActivity
import fr.imacaron.groupe19.urgan.home.games
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.*

class LikesFragment: Fragment() {
    private lateinit var binding: FragmentLikeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val adapter = GameAdapter((activity as HomeActivity).user.likeList.orEmpty(), this)
        binding.list.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
