package fr.imacaron.groupe19.urgan.backend.steam

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.imacaron.groupe19.urgan.backend.steam.response.GameDetailsResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.GameReviewResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.MostPlayedGameResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object SteamAPIManager {

    val store = Retrofit.Builder()
        .baseUrl("https://store.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    val api = Retrofit.Builder()
        .baseUrl("https://api.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    suspend fun getMostPlayedGames(): MostPlayedGameResponse = api.getMostPlayedGames().await()

    suspend fun getGameDetails(id : Int): GameDetailsResponse {
        val response = store.getGameDetails(id).await()
        val game_details = response.asJsonObject.getAsJsonObject(id.toString())
        println(game_details.toString())
        return GsonBuilder().create().fromJson(game_details, GameDetailsResponse::class.java)
    }

    suspend fun getReviewFromGame(id: Int): GameReviewResponse = store.getReviewFromGame(id).await()

}


