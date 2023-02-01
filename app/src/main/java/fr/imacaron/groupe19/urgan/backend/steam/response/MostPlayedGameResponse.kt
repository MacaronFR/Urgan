package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName

data class MostPlayedGameResponse(
    @SerializedName("response") val response : Response
) {
    data class Response(
        @SerializedName("rollup_date"   ) val rollup_date : Long,
        @SerializedName("ranks"         ) val ranks : List<MostPlayedGame>
    ) {
        data class MostPlayedGame(
            @SerializedName("rank"          ) val rank : Int,
            @SerializedName("appid"         ) val app_id : Long,
            @SerializedName("last_week_rank") val last_week_rank : Int,
            @SerializedName("peak_in_game"  ) val peak_in_game : Int
        )
    }
}
