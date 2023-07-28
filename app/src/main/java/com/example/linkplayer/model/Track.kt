package com.example.linkplayer.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(
    @Json(name = "title")
    val title: String,
    @Json(name = "artist")
    val artist: String,
    @Json(name = "bitmapUri")
    val bitmapUri: String?,
    @Json(name = "trackUri")
    val trackUri: String?
): Parcelable

//@Serializable
//data class Track(
//    @SerialName("title")
//    val title: String,
//    @SerialName("artist")
//    val artist: String,
//    @SerialName("bitmapUri")
//    val bitmapUri: String?,
//    @SerialName("trackUri")
//    val trackUri: String?
//)