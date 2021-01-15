package com.example.splashapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoverPhoto(
    val id: String,
    @Json(name = "alt_description")
    val altDescription: String? = null,
    val description: String? = null,
    val height: Int,
    val width: Int,
    val urls: Urls
) : Parcelable