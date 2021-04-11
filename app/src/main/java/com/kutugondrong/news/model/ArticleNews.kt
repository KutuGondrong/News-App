package com.kutugondrong.news.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ArticleNews {

    data class ArticleNewsResponse(
        @SerializedName("articles")
        val articles: ArrayList<ArticleResponse>
    )

    data class ArticleResponse(
        @SerializedName("author")
        val author: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("urlToImage")
        val urlToImage: String?,
        @SerializedName("content")
        val content: String?
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(author)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeString(urlToImage)
            parcel.writeString(content)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ArticleResponse> {
            override fun createFromParcel(parcel: Parcel): ArticleResponse {
                return ArticleResponse(parcel)
            }

            override fun newArray(size: Int): Array<ArticleResponse?> {
                return arrayOfNulls(size)
            }
        }

    }

}