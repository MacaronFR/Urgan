package fr.imacaron.groupe19.urgan.common

import com.google.gson.annotations.SerializedName

data class MostPlayedGame(

    val rank : Int,

    @SerializedName("appid")
    val app_id : Int,

    val last_week_rank : Int,

    val peak_in_game : Int
)
