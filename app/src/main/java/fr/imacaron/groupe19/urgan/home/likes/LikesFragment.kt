package fr.imacaron.groupe19.urgan.home.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.databinding.FragmentLikeBinding
import fr.imacaron.groupe19.urgan.home.HomeActivity
import fr.imacaron.groupe19.urgan.list.GameAdapter

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
