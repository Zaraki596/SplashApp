package com.example.splashapp.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoResponse(
    val id: String? = null,
    val description: String? = null,
    val urls: Urls? = null,
) : Parcelable