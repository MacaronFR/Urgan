package fr.imacaron.groupe19.urgan.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.data.Review
import fr.imacaron.groupe19.urgan.error.NetworkException
import fr.imacaron.groupe19.urgan.error.h
import fr.imacaron.groupe19.urgan.views.UnderLineTextView
import kotlinx.coroutines.*

class ReviewAdapter(private val dataSet: List<Review>, private val fragment: Fragment): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

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

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSet[position]) review@{
            holder.review.text = this.review
            GlobalScope.launch {
                withContext(Dispatchers.IO + h){
                    val u = try {
                        SteamAPIManager.getPlayerDetails(this@review.player.toLong())
                    }catch (e: NetworkException){
                        withContext(Dispatchers.Main){
                            Toast.makeText(fragment.context, "Pas internet", Toast.LENGTH_SHORT)
                        }
                        return@withContext
                    }
                    withContext(Dispatchers.Main){
                        holder.name.text = u.response.players[0].name
                    }
                }
            }
        }
    }
}