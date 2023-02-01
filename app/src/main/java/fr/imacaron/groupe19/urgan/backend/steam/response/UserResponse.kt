package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("response") val response: Response
){
    data class Response(
        @SerializedName("players") val players: List<Player>
    )
}

data class Player (
    @SerializedName("personaname") val name: String
)