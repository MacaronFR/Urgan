package fr.imacaron.groupe19.urgan.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.databinding.FragmentSearchBinding
import fr.imacaron.groupe19.urgan.home.games
import fr.imacaron.groupe19.urgan.list.GameAdapter

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val adapter = GameAdapter(listOf()) {
            println("ICI")
            setFragmentResult("gameData", bundleOf("data" to games[0]))
            findNavController().navigate(R.id.DetailFragment)
        }

        binding.list.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.requestFocus()
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
