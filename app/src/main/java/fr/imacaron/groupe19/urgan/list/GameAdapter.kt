package fr.imacaron.groupe19.urgan.list

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.backend.steam.SteamAPIManager
import fr.imacaron.groupe19.urgan.data.Game
import fr.imacaron.groupe19.urgan.data.Review
import fr.imacaron.groupe19.urgan.error.h
import kotlinx.coroutines.*
import java.net.MalformedURLException
import java.net.URL

class GameAdapter(private val dataSet: List<Long>, val fragment: Fragment): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView
        val editor: TextView
        val price: TextView
        val picture: ImageView
        val background: ImageView
        val more: TextView

        init {
            title = view.findViewById(R.id.title)
            editor = view.findViewById(R.id.editor)
            price = view.findViewById(R.id.price)
            picture = view.findViewById(R.id.logo)
            background = view.findViewById(R.id.background)
            more = view.findViewById(R.id.more)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_row_item, parent, false)
        return ViewHolder(view)
    }

    private var apiRequest: MutableMap<Int, Job> = mutableMapOf()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet[position].let { gameId ->
            var game: Game
            apiRequest[holder.layoutPosition] = GlobalScope.launch {
                withContext(Dispatchers.IO + h) {
                    val game_details = SteamAPIManager.getGameDetails(gameId)
                    val game_reviews_response = SteamAPIManager.getGameReviews(gameId)
                    val game_reviews = game_reviews_response.reviews.map {
                        Review(
                            it.author?.steamid.toString(),
                            it.votedUp ?: false,
                            it.review ?: "No review"
                        )
                    } as ArrayList
                    game = Game(
                        game_details.data?.name ?: "",
                        game_details.data?.publishers?.getOrNull(0) ?: "",
                        if (game_details.data?.isFree == true) "free" else (game_details.data?.priceOverview?.finalFormatted ?: "-€"),
                        game_details.data?.headerImage ?: "",
                        game_details.data?.background ?: "",
                        game_details.data?.shortDescription ?: "",
                        game_details.data?.headerImage ?: "",
                        true,
                        true,
                        game_reviews,
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

                    try{
                        val url = URL(game.background)
                        val bmp = BitmapFactory.decodeStream(url.openStream())
                        withContext(Dispatchers.Main){
                            holder.background.setImageBitmap(bmp)
                        }
                    }catch (e: MalformedURLException){
                        e.printStackTrace()
                    }
                }
                withContext(Dispatchers.Main) {
                    holder.more.setOnClickListener{
                        fragment.setFragmentResult("gameData", bundleOf("data" to game))
                        fragment.findNavController().navigate(R.id.DetailFragment)
                    }
                    holder.title.text = game.title
                    holder.editor.text = game.editor
                    holder.price.text = fragment.requireContext().resources.getString(R.string.price, game.price)
                }
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        apiRequest[holder.layoutPosition]?.cancel()
    }

    override fun getItemCount(): Int = dataSet.size
}
