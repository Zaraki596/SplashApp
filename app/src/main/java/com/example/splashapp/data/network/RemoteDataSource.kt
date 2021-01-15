package com.example.splashapp.data.network

import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import retrofit2.Response

class RemoteDataSource(val unsplashApiService: UnsplashApiService) {
    suspend fun getCollections(pageNumber: Int): Response<List<CollectionResponse>> =
        unsplashApiService.getListCollections(pageNumber)

    suspend fun getPhotos(id: String): Response<List<PhotoResponse>> =
        unsplashApiService.getListPhotos(id)
}