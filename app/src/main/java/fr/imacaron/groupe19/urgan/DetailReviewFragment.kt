package fr.imacaron.groupe19.urgan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.imacaron.groupe19.urgan.data.Review
import fr.imacaron.groupe19.urgan.databinding.FragmentDetailReviewBinding
import fr.imacaron.groupe19.urgan.list.ReviewAdapter

class DetailReviewFragment: Fragment() {
    private lateinit var binding: FragmentDetailReviewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println("Coucou")
        binding = FragmentDetailReviewBinding.inflate(inflater, container, false)
        val reviews = arguments?.getParcelableArrayList<Review>("review")
        binding.list.adapter = ReviewAdapter(reviews?.toList() ?: listOf())
        return binding.root
    }
}