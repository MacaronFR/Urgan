package fr.imacaron.groupe19.urgan.data

data class User(
    val pseudo: String? = null,
    val email: String? = null,
    val password: String? = null,
    val wishList: ArrayList<Long>? = null,
    val likeList: ArrayList<Long>? = null
)
