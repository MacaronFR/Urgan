package fr.imacaron.groupe19.urgan.home.detail

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.firebase.FirebaseAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentDetailGameBinding
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.home.HomeActivity
import kotlinx.coroutines.*
import java.net.MalformedURLException
import java.net.URL

class DetailFragment: Fragment() {
    private lateinit var binding: FragmentDetailGameBinding

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("gameData"){ _, bundle ->
            game = if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                bundle.getParcelable("data", Game::class.java)!!
            }else {
                bundle.getParcelable("data")!!
            }
            setData()
            updateWish()
            updateLike()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.toDesc.setOnClickListener {
            binding.toDesc.setBackgroundResource(R.drawable.button_first_group_left)
            binding.toReviews.setBackgroundResource(R.drawable.button_second_group_right)
            binding.descReviews.findNavController().navigate(R.id.DetailDescFragment, bundleOf("game" to game))
        }

        binding.toReviews.setOnClickListener {
            binding.toDesc.setBackgroundResource(R.drawable.button_second_group_left)
            binding.toReviews.setBackgroundResource(R.drawable.button_first_group_right)
            binding.descReviews.findNavController().navigate(R.id.DetailReviewFragment, bundleOf("review" to game.reviews))
        }

        binding.like.setOnClickListener {
            GlobalScope.launch {
                withContext(Dispatchers.IO + h) {
                    if ((activity as HomeActivity).user.likeList?.contains(game.id.toLong()) == false) {
                        (activity as HomeActivity).user.likeList?.add(game.id.toLong())
                    }
                    else {
                        (activity as HomeActivity).user.likeList?.remove(game.id.toLong())
                    }
                    FirebaseAPIManager.setUser((activity as HomeActivity).user)

                    withContext(Dispatchers.Main) {
                        updateLike()
                    }
                }
            }
        }

        binding.wishlist.setOnClickListener {
            GlobalScope.launch {
                withContext(Dispatchers.IO + h) {
                    if ((activity as HomeActivity).user.wishList?.contains(game.id.toLong()) == false) {
                        (activity as HomeActivity).user.wishList?.add(game.id.toLong())
                    }
                    else {
                        (activity as HomeActivity).user.wishList?.remove(game.id.toLong())
                    }
                    FirebaseAPIManager.setUser((activity as HomeActivity).user)

                    withContext(Dispatchers.Main) {
                        updateWish()
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun setData() {
        binding.title.text = game.title
        binding.editor.text = game.editor
        GlobalScope.launch(Dispatchers.IO){
            try{
                val url = URL(game.picture)
                val bmp = BitmapFactory.decodeStream(url.openStream())
                withContext(Dispatchers.Main){
                    binding.logo.setImageBitmap(bmp)
                }
            }catch (e: MalformedURLException){
                e.printStackTrace()
            }
            try{
                val url = URL(game.banner)
                val bmp = BitmapFactory.decodeStream(url.openStream())
                withContext(Dispatchers.Main){
                    binding.banner.setImageBitmap(bmp)
                }
            }catch (e: MalformedURLException){
                e.printStackTrace()
            }
        }
        binding.descReviews.findNavController().navigate(R.id.DetailDescFragment, bundleOf("game" to game))
        if(game.like){
            binding.like.setImageResource(R.drawable.like_full)
        }
        if(game.wish){
            binding.wishlist.setImageResource(R.drawable.whishlist_full)
        }
    }

    private fun updateWish() {
        if ((activity as HomeActivity).user.wishList?.contains(game.id.toLong()) == true) {
            binding.wishlist.setImageResource(R.drawable.whishlist_full)
        }
        else {
            binding.wishlist.setImageResource(R.drawable.whishlist)
        }
    }

    private fun updateLike() {
        if ((activity as HomeActivity).user.likeList?.contains(game.id.toLong()) == true) {
            binding.like.setImageResource(R.drawable.like_full)
        }
        else {
            binding.like.setImageResource(R.drawable.like)
        }
    }
}