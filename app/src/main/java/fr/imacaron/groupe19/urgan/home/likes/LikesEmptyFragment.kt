package fr.imacaron.groupe19.urgan.home.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.databinding.FragmentEmptyLikeBinding

class LikesEmptyFragment: Fragment() {
    private lateinit var binding: FragmentEmptyLikeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEmptyLikeBinding.inflate(inflater, container, false)
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