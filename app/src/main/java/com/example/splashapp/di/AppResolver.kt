package com.example.splashapp.di

import com.example.splashapp.data.network.ApiManager
import com.example.splashapp.data.network.RemoteDataSource
import com.example.splashapp.data.repo.UnsplashRepository

object AppResolver {
    fun provieRemoteDataSource(apiManager: ApiManager) =
        RemoteDataSource(apiManager.unsplashApiService)

    fun provideUnsplashRepository(remoteDataSource: RemoteDataSource) =
        UnsplashRepository(remoteDataSource)
}