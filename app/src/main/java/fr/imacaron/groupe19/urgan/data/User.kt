package fr.imacaron.groupe19.urgan.data

import android.os.Parcel
import android.os.Parcelable

data class User(
    val pseudo: String? = null,
    val email: String? = null,
    val password: String? = null,
    val wishList: ArrayList<Long>? = null,
    val likeList: ArrayList<Long>? = null
): Parcelable {
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createLongArray()?.toCollection(ArrayList()),
        parcel.createLongArray()?.toCollection(ArrayList()),
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(pseudo)
        dest.writeString(email)
        dest.writeString(password)
        dest.writeLongArray(wishList?.toLongArray())
        dest.writeLongArray(likeList?.toLongArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<User> {
        override fun createFromParcel(source: Parcel): User {
            return User(source)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
