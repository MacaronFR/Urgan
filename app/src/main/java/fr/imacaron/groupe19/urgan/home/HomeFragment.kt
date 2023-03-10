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
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.list.GameAdapter
import kotlinx.coroutines.*

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.spinnerLoading.root.visibility = View.VISIBLE
        binding.spinnerLoading.root.bringToFront()

        val adapter = GameAdapter(listOf(), this)
        binding.list.adapter = adapter

        GlobalScope.launch {
            withContext(Dispatchers.IO + h) {
                val res = FirebaseAPIManager.getCurrentUser()
                if (res != null) {
                    (activity as HomeActivity).user = res
                    withContext(Dispatchers.Main) {
                        updateWishCount()
                        updateLikeCount()
                    }
                }
            }
        }
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.SearchFragment)
        }

        binding.wishlist.setOnClickListener {
            if((activity as HomeActivity).user.wishList?.size == 0){
                findNavController().navigate(R.id.WishlistEmptyFragment)
            }else{
                findNavController().navigate(R.id.WishlistFragment)
            }
        }

        binding.like.setOnClickListener {
            if((activity as HomeActivity).user.likeList?.size == 0){
                findNavController().navigate(R.id.LikesEmptyFragment)
            }else{
                findNavController().navigate(R.id.LikesFragment)
            }
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
                val gamesDetailsIds = games.response.ranks.map {
                    it.app_id
                }

                withContext(Dispatchers.Main) {
                    val adapter = GameAdapter(gamesDetailsIds, this@HomeFragment)
                    binding.list.adapter = adapter
                    binding.spinnerLoading.root.visibility = View.GONE
                }
                0
            }
            withContext(Dispatchers.Main){
                when(res){
                    1 -> Toast.makeText(context, resources.getString(R.string.no_connection), Toast.LENGTH_LONG).show()
                }
                binding.spinnerLoading.root.visibility = View.GONE
            }
        }

    }

    private fun updateWishCount() {
        val count = (activity as HomeActivity).user.wishList?.size
        if(count == null || count <= 0){
            binding.wishCount.visibility = View.INVISIBLE
        }else{
            binding.wishCount.text = count.toString()
            binding.wishCount.visibility = View.VISIBLE
        }
    }

    private fun updateLikeCount() {
        val count = (activity as HomeActivity).user.likeList?.size
        if(count == null || count <= 0){
            binding.likeCount.visibility = View.INVISIBLE
        }else{
            binding.likeCount.text = count.toString()
            binding.likeCount.visibility = View.VISIBLE
        }
    }

}
