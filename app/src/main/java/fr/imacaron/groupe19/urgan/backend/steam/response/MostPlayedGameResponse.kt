package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName
import fr.imacaron.groupe19.urgan.common.MostPlayedGame

data class MostPlayedGameResponse(
    @SerializedName("response" )
    val response : Response

) {
    data class Response(
        @SerializedName("rollup_date" )
        val rollup_date : Long,

        @SerializedName("ranks" )
        val ranks : List<MostPlayedGame>

    )
}
