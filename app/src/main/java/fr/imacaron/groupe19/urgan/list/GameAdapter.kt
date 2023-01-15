package fr.imacaron.groupe19.urgan.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.data.Game

class GameAdapter(private val dataSet: List<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView
        val editor: TextView
        val price: TextView
        val picture: ImageView

        init {
            title = view.findViewById(R.id.title)
            editor = view.findViewById(R.id.editor)
            price = view.findViewById(R.id.price)
            picture = view.findViewById(R.id.logo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSet[position]){
            holder.title.text = title
            holder.editor.text = editor
            holder.price.text = "Prix : ${price}€"
        }
    }

    override fun getItemCount(): Int = dataSet.size
}
