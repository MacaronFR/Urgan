package fr.imacaron.groupe19.urgan.list

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.databinding.FragmentDetailGameBinding
import fr.imacaron.groupe19.urgan.databinding.GameRowItemBinding
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.net.MalformedURLException
import java.net.URL

class GameAdapter(private val dataSet: List<iNT>, val onClick: View.OnClickListener): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

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
            var game = Game("title", "editor", 0.0, "picture", "description", "banner", false, false, listOf(), 0)
            holder.more.setOnClickListener(onClick)
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    val game_details = SteamAPIManager.getGameDetails(this@with)
                    game = Game(
                        game_details.data?.name ?: "",
                        game_details.data?.developers?.getOrNull(0) ?: "",
                        game_details.data?.priceOverview?.final?.toDouble() ?: 0.0,
                        game_details.data?.headerImage ?: "",
                        game_details.data?.shortDescription ?: "",
                        "",
                        false,
                        true,
                        listOf(),
                        game_details.data?.steamAppid ?: 0
                    )

                    try{
                        val url = URL(game.picture)
                        val bmp = BitmapFactory.decodeStream(url.openStream())
                        withContext(Dispatchers.Main){
                            holder.picture.setImageBitmap(bmp)
                        }
                    }catch (e: MalformedURLException){
                        e.printStackTrace()
                    }
                }
                withContext(Dispatchers.Main) {
                    holder.title.text = game.title
                    holder.editor.text = game.editor
                    holder.price.text = "Prix : ${game.price/100}€"
                }
            }

            GlobalScope.launch(Dispatchers.IO){

            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}
