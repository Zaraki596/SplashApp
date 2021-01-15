package com.example.splashapp.data.network

import com.example.splashapp.BuildConfig
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("collections")
    suspend fun getListCollections(
        @Query("page") page: Int = 1,
        @Query("client_id") accessKey: String = BuildConfig.ACCESS_KEY
    ): Response<List<CollectionResponse>>

    @GET("collections/{id}/photos")
    suspend fun getListPhotos(
        @Path("id") id: String,
        @Query("client_id") accessKey: String = BuildConfig.ACCESS_KEY
    ): Response<List<PhotoResponse>>
}