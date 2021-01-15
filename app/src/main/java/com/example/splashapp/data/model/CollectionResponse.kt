package com.example.splashapp.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionResponse(
    val id: String,
    val title: String? = null,
    val cover_photo: CoverPhoto,
    @Json(name = "description")
    val description: String? = null,
    val links: Links,
    val total_photos: Int
) : Parcelable