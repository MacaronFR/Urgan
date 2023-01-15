package fr.imacaron.groupe19.urgan.data

data class Game(
    val title: String,
    val editor: String,
    val price: Double,
    val picture: String,
    val description: String,
    val banner: String,
    val wish: Boolean,
    val like: Boolean,
    val reviews: List<Review>,
    val id: Int
)
