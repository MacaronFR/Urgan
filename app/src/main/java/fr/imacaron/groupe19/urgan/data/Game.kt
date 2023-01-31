package fr.imacaron.groupe19.urgan.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Game(
    val title: String,
    val editor: String,
    val price: String,
    val picture: String,
    val background: String,
    val description: String,
    val banner: String,
    val wish: Boolean,
    val like: Boolean,
    val reviews: ArrayList<Review>,
    val id: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.createTypedArrayList(Review.CREATOR)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(editor)
        parcel.writeString(price)
        parcel.writeString(picture)
        parcel.writeString(background)
        parcel.writeString(description)
        parcel.writeString(banner)
        parcel.writeByte(if (wish) 1 else 0)
        parcel.writeByte(if (like) 1 else 0)
        parcel.writeParcelableArray(reviews.toTypedArray(), 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}
