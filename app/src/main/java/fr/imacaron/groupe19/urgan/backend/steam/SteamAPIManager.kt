package fr.imacaron.groupe19.urgan.backend.steam

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.imacaron.groupe19.urgan.backend.steam.response.GameDetailsResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.GameReviewResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.MostPlayedGameResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.AppSearchResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object SteamAPIManager {

    private val community = Retrofit.Builder()
        .baseUrl("https://steamcommunity.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    private val store = Retrofit.Builder()
        .baseUrl("https://store.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    private val api = Retrofit.Builder()
        .baseUrl("https://api.steampowered.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(SteamAPI::class.java)

    suspend fun getMostPlayedGames(): MostPlayedGameResponse = api.getMostPlayedGames().await()

    suspend fun getGameDetails(id : Long): GameDetailsResponse {
        val response = store.getGameDetailsById(id).await()
        val game_details = response.asJsonObject.getAsJsonObject(id.toString())
        println(game_details.toString())
        return GsonBuilder().create().fromJson(game_details, GameDetailsResponse::class.java)
    }

    suspend fun getGameReviews(id: Long): GameReviewResponse = store.getGameReviewsById(id).await()


    suspend fun getAppsByName(name: String): List<AppSearchResponse> = community.getAppsByName(name).await()

}


