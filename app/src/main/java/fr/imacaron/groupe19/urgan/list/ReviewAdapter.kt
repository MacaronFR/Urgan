package fr.imacaron.groupe19.urgan.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.data.Review
import fr.imacaron.groupe19.urgan.views.UnderLineTextView

class ReviewAdapter(private val dataSet: List<Review>): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val review: TextView
        val name: UnderLineTextView

        init {
            review = view.findViewById(R.id.review)
            name = view.findViewById(R.id.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSet[position]) {
            holder.review.text = this.review
            holder.name.text = this.player
        }
    }
}