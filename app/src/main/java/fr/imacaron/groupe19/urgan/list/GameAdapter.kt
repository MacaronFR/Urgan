package fr.imacaron.groupe19.urgan.list

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentDetailGameBinding
import fr.imacaron.groupe19.urgan.databinding.GameRowItemBinding
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.net.MalformedURLException
import java.net.URL

class GameAdapter(private val dataSet: List<Game>, val onClick: View.OnClickListener): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView
        val editor: TextView
        val price: TextView
        val picture: ImageView
        val more: TextView

        init {
            title = view.findViewById(R.id.title)
            editor = view.findViewById(R.id.editor)
            price = view.findViewById(R.id.price)
            picture = view.findViewById(R.id.logo)
            more = view.findViewById(R.id.more)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_row_item, parent, false)
        return ViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSet[position]){
            holder.title.text = title
            holder.editor.text = editor
            holder.price.text = "Prix : ${price}€"
            holder.more.setOnClickListener(onClick)
            GlobalScope.launch(Dispatchers.IO){
                try{
                    val url = URL(picture)
                    val bmp = BitmapFactory.decodeStream(url.openStream())
                    withContext(Dispatchers.Main){
                        holder.picture.setImageBitmap(bmp)
                    }
                }catch (e: MalformedURLException){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}
