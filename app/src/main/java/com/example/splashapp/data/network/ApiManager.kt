package com.example.splashapp.data.network

import retrofit2.Retrofit

class ApiManager(private val retrofit: Retrofit) {
    val unsplashApiService: UnsplashApiService by lazy {
        retrofit.create(UnsplashApiService::class.java)
    }
}