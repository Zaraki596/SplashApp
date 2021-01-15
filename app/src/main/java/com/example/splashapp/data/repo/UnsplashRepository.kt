package com.example.splashapp.data.repo

import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.data.network.RemoteDataSource
import retrofit2.Response

class UnsplashRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getCollection(page: Int): Response<List<CollectionResponse>> =
        remoteDataSource.getCollections(page)

    suspend fun getPhotos(id: String): Response<List<PhotoResponse>> =
        remoteDataSource.getPhotos(id)
}