package fr.imacaron.groupe19.urgan.backend.steam

import fr.imacaron.groupe19.urgan.backend.steam.response.GameDetailsResponse
import fr.imacaron.groupe19.urgan.backend.steam.response.MostPlayedGameResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamAPI {

    @GET("/ISteamChartsService/GetMostPlayedGames/v1")
    fun getMostPlayedGames() : Deferred<MostPlayedGameResponse>

    @GET("/api/appdetails")
    fun getGameDetails(@Query("appids") id : Int) : Deferred<GameDetailsResponse>

}