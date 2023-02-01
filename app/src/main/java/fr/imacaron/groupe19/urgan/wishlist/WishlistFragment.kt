package fr.imacaron.groupe19.urgan.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentWishlistBinding
import fr.imacaron.groupe19.urgan.error.NetworkException
import fr.imacaron.groupe19.urgan.home.HomeActivity
import fr.imacaron.groupe19.urgan.home.games
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.*

class WishlistFragment: Fragment() {
    private lateinit var binding: FragmentWishlistBinding

    private lateinit var wishGames: List<Game>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val adapter = GameAdapter((activity as HomeActivity).user.wishList.orEmpty(), this)
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