package com.example.splashapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val download: String? = null,
    @Json(name = "download_location")
    val downloadLocation: String? = null,
    val html: String,
    val self: String
) : Parcelable
