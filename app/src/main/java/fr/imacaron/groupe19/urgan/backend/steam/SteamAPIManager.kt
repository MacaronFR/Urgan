package fr.imacaron.groupe19.urgan.backend.steam

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import fr.imacaron.groupe19.urgan.backend.steam.response.*
import fr.imacaron.groupe19.urgan.error.NetworkException
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import kotlin.Exception

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

    suspend fun getMostPlayedGames(): MostPlayedGameResponse {
        return try {
            api.getMostPlayedGames().await()
        }catch (e: Exception){
            when(e) {
                is UnknownHostException -> {
                    throw NetworkException("Cannot resolve DNS")
                }
                else -> {
                    e.printStackTrace()
                }
            }
            MostPlayedGameResponse(MostPlayedGameResponse.Response(0, listOf()))
        }
    }

    suspend fun getGameDetails(id : Long): GameDetailsResponse {
        return try{
            val response = store.getGameDetailsById(id).await()
            val game_details = response.asJsonObject.getAsJsonObject(id.toString())
            GsonBuilder().create().fromJson(game_details, GameDetailsResponse::class.java)
        }catch (e: Exception){
            when(e) {
                is UnknownHostException -> {
                    throw NetworkException("Cannot resolve DNS")
                }
                else -> {
                    e.printStackTrace()
                    throw e
                }
            }
        }
    }

    suspend fun getGameReviews(id: Long): GameReviewResponse{
        return try {
            store.getGameReviewsById(id).await()
        }catch (e: Exception){
            when(e) {
                is UnknownHostException -> throw NetworkException("Cannot resolve DNS")
                else -> {
                    e.printStackTrace()
                    throw e
                }
            }
        }
    }


    suspend fun getAppsByName(name: String): List<AppSearchResponse> {
        return try {
            community.getAppsByName(name).await()
        }catch (e: Exception){
            when(e) {
                is UnknownHostException -> {
                    throw NetworkException("Cannot resolve DNS")
                }
                else -> {
                    e.printStackTrace()
                }
            }
            listOf()
        }
    }

    suspend fun getPlayerDetails(id: Long): UserResponse {
        return api.getPlayerDetail(id).await()
    }

}


