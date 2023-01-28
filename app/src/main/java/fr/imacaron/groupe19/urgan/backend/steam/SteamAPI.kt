package fr.imacaron.groupe19.urgan.backend.steam

import com.google.gson.JsonElement
import fr.imacaron.groupe19.urgan.backend.steam.response.GameDetailsResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.GameReviewResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.MostPlayedGameResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SteamAPI {

    @GET("/ISteamChartsService/GetMostPlayedGames/v1")
    fun getMostPlayedGames() : Deferred<MostPlayedGameResponse>

    @GET("/api/appdetails")
    fun getGameDetails(@Query("appids") id : Int) : Call<JsonElement>

    @GET("/appreviews/{app_id}?json=1")
    fun getReviewFromGame(@Path("app_id") id : Int) : Deferred<GameReviewResponse>

}