package fr.imacaron.groupe19.urgan.home.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.databinding.FragmentEmptyWishlistBinding

class WishlistEmptyFragment: Fragment() {
    private lateinit var binding: FragmentEmptyWishlistBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEmptyWishlistBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}