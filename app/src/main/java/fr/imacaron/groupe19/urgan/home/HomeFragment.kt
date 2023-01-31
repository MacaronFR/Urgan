package fr.imacaron.groupe19.urgan.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.databinding.FragmentHomeBinding
import fr.imacaron.groupe19.urgan.error.NetworkException
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.*

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val adapter = GameAdapter(listOf(), this)
        binding.list.adapter = adapter

        updateWishCount(0)
        updateLikeCount(0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.SearchFragment)
        }

        binding.wishlist.setOnClickListener {
            findNavController().navigate(R.id.WishlistFragment)
        }

        binding.like.setOnClickListener {
            findNavController().navigate(R.id.LikesFragment)
        }

        GlobalScope.launch {
            val h = CoroutineExceptionHandler{ _, t ->
                t.printStackTrace()
            }
            val res = withContext(Dispatchers.IO + h) {
                val games = try {
                    SteamAPIManager.getMostPlayedGames()
                }catch (e: NetworkException){
                    return@withContext 1
                }
                val games_details_ids = games.response.ranks.map {
                    it.app_id
                }

                val user = FirebaseAPIManager.getCurrentUser()

                withContext(Dispatchers.Main) {
                    val adapter = GameAdapter(games_details_ids, this@HomeFragment)
                    binding.list.adapter = adapter

                    updateWishCount(user?.wishList?.size ?: -1)
                    updateLikeCount(user?.wishList?.size ?: -1)

                }
                0
            }
            when(res){
                1 -> Toast.makeText(context, "Pas de connexion internet", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun updateWishCount(count: Int) {
        if(count == 0){
            binding.wishCount.visibility = View.INVISIBLE
        }else{
            binding.wishCount.text = count.toString()
            binding.wishCount.visibility = View.VISIBLE
        }
    }

    fun updateLikeCount(count: Int) {
        if(count == 0){
            binding.likeCount.visibility = View.INVISIBLE
        }else{
            binding.likeCount.text = count.toString()
            binding.wishCount.visibility = View.VISIBLE
        }
    }

}
