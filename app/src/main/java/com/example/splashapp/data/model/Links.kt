package com.example.splashapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val download: String,
    @Json(name = "download_location")
    val downloadLocation: String,
    val html: String,
    val self: String
) : Parcelable