package fr.imacaron.groupe19.urgan.data

import android.os.Parcel
import android.os.Parcelable

data class Review(
    val player: String,
    val like: Boolean,
    val review: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(player)
        parcel.writeByte(if (like) 1 else 0)
        parcel.writeString(review)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}
