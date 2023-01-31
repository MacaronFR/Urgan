package fr.imacaron.groupe19.urgan.backend.steam.response

import com.google.gson.annotations.SerializedName

data class AppSearchResponse(
    @SerializedName("appid" ) var appid : String? = null,
    @SerializedName("name"  ) var name  : String? = null,
    @SerializedName("icon"  ) var icon  : String? = null,
    @SerializedName("logo"  ) var logo  : String? = null
)
