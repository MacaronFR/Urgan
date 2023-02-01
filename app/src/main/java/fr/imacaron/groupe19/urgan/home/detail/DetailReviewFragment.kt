package fr.imacaron.groupe19.urgan.home.detail

import android.os.Build
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
        binding = FragmentDetailReviewBinding.inflate(inflater, container, false)
        val reviews = if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelableArrayList("review", Review::class.java) ?: listOf()
        }else {
            println("old")
            arguments?.getParcelableArrayList("review") ?: listOf()
        }
        println(reviews.size)
        binding.list.adapter = ReviewAdapter(reviews.toList())
        return binding.root
    }
}