package fr.imacaron.groupe19.urgan.backend.steam

import com.google.gson.JsonElement
import fr.imacaron.groupe19.urgan.backend.steam.response.AppSearchResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.GameReviewResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.MostPlayedGameResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SteamAPI {

    @GET("/ISteamChartsService/GetMostPlayedGames/v1")
    fun getMostPlayedGames() : Deferred<MostPlayedGameResponse>

    @GET("/api/appdetails")
    fun getGameDetailsById(@Query("appids") id : Long, @Query("l") lang: String = "french") : Call<JsonElement>

    @GET("/appreviews/{app_id}?json=1")
    fun getGameReviewsById(@Path("app_id") id : Long) : Deferred<GameReviewResponse>

    @GET("/actions/SearchApps/{name}")
    fun getAppsByName(@Path("name") name: String) : Deferred<List<AppSearchResponse>>

    @GET("/ISteamUser/GetPlayerSummaries/v2")
    fun getPlayerDetail(@Query("steamids") id: Long, @Query("key") key: String = "0016FA008ECF7498F1756C662B614525", @Query("format") format: String = "json"): Deferred<UserResponse>

}