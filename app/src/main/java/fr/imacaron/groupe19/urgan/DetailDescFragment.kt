package fr.imacaron.groupe19.urgan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentDetailDescBinding

class DetailDescFragment: Fragment() {
    private lateinit var binding: FragmentDetailDescBinding
    var desc: TextView? = null
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailDescBinding.inflate(inflater, container, false)
        val game = arguments?.getParcelable<Game>("game")
        binding.desc.text = game?.description
        desc = binding.desc
        return binding.root
    }
}