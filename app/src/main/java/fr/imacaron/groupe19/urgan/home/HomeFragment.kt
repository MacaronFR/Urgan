package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentHomeBinding
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var wishCount = 10
    private var likesCount = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val adapter = GameAdapter(listOf()) { findNavController().navigate(R.id.DetailFragment) }
        binding.list.adapter = adapter

        if(wishCount == 0){
            binding.wishCount.visibility = View.INVISIBLE
        }else{
            binding.wishCount.text = wishCount.toString()
        }
        if(likesCount == 0){
            binding.likeCount.visibility = View.INVISIBLE
        }else{
            binding.likeCount.text = likesCount.toString()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.SearchFragment)
        }

        binding.wishlist.setOnClickListener {
            if(wishCount == 0){
                findNavController().navigate(R.id.WishlistEmptyFragment)
            }else{
                findNavController().navigate(R.id.WishlistFragment)
            }
        }

        binding.like.setOnClickListener {
            if(likesCount == 0){
                findNavController().navigate(R.id.LikesEmptyFragment)
            }else{
                findNavController().navigate(R.id.LikesFragment)
            }
        }

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                var games = SteamAPIManager.getMostPlayedGames()
                var games_details_ids = games.response.ranks.map {
                    it.app_id
                }

                FirebaseAPIManager.getWishListIds()

                withContext(Dispatchers.Main) {
                    binding.search.text = games.response.ranks[0].app_id.toString()
                    val adapter = GameAdapter(games_details_ids) { findNavController().navigate(R.id.DetailFragment) }
                    binding.list.adapter = adapter
                }

            }
        }

    }
}
