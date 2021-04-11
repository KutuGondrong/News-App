package com.kutugondrong.news.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class SourceNews {
    data class SourceNewsResponse(
        @SerializedName("sources")
        val sources: ArrayList<SourceResponse>
    )

    data class SourceResponse(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("category")
        val category: String?
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(category)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SourceResponse> {
            override fun createFromParcel(parcel: Parcel): SourceResponse {
                return SourceResponse(parcel)
            }

            override fun newArray(size: Int): Array<SourceResponse?> {
                return arrayOfNulls(size)
            }
        }
    }

}