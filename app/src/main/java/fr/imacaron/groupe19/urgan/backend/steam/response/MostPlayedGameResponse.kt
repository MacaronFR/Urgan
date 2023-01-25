package fr.imacaron.groupe19.urgan.backend.steam.response

import fr.imacaron.groupe19.urgan.common.MostPlayedGame

data class MostPlayedGameResponse(

    val rollup_date : Int,

    val ranks : List<MostPlayedGame>

)
