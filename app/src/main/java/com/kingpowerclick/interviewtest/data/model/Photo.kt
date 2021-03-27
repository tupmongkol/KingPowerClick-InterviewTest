package com.kingpowerclick.interviewtest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Photo : Serializable {
    @SerializedName("albumId")
    val albumId: Int = 0

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("title")
    val title: String = ""

    @SerializedName("url")
    val url: String = ""

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String = ""
}