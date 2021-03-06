package `in`.rab.tsplex

import android.os.Parcel
import android.os.Parcelable

class Explanation internal constructor(internal val video: String,
                                       private val description: String) : Parcelable, Item() {
    override fun toString(): String {
        return description
    }

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(video)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Explanation> {
        override fun createFromParcel(parcel: Parcel): Explanation {
            return Explanation(parcel)
        }

        override fun newArray(size: Int): Array<Explanation?> {
            return arrayOfNulls(size)
        }
    }
}