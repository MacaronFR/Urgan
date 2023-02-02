package fr.imacaron.groupe19.urgan.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import java.util.Random

class ReviewAdapter(private val dataSet: List<Review>, private val fragment: Fragment): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val review: TextView
        val name: UnderLineTextView
        val star1: ImageView
        val star2: ImageView
        val star3: ImageView
        val star4: ImageView
        val star5: ImageView

        init {
            review = view.findViewById(R.id.review)
            name = view.findViewById(R.id.name)
            star1 = view.findViewById(R.id.star1)
            star2 = view.findViewById(R.id.star2)
            star3 = view.findViewById(R.id.star3)
            star4 = view.findViewById(R.id.star4)
            star5 = view.findViewById(R.id.star5)
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
            stars(holder, this.like)
            GlobalScope.launch {
                withContext(Dispatchers.IO + h){
                    val u = try {
                        SteamAPIManager.getPlayerDetails(this@review.player.toLong())
                    }catch (e: NetworkException){
                        withContext(Dispatchers.Main){
                            Toast.makeText(fragment.context, fragment.resources.getString(R.string.no_connection), Toast.LENGTH_SHORT)
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

    private fun stars(holder: ViewHolder, like: Boolean){
        val delta = Random().nextInt(3)
        if(like){
            holder.star1.setImageResource(R.drawable.star_full)
            holder.star2.setImageResource(R.drawable.star_full)
            holder.star3.setImageResource(R.drawable.star_full)
            if(delta >= 1){
                holder.star4.setImageResource(R.drawable.star_full)
            }
            if(delta >= 2){
                holder.star5.setImageResource(R.drawable.star_full)
            }
        }else{
            if(delta >= 1){
                holder.star1.setImageResource(R.drawable.star_full)
            }
            if(delta >= 2){
                holder.star2.setImageResource(R.drawable.star_full)
            }
        }
    }
}