package com.example.splashapp.di

import com.example.splashapp.data.network.ApiManager
import com.example.splashapp.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkResolver {

    fun provideApiManager(retrofit: Retrofit) = ApiManager(retrofit)

    fun provideRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun provideBaseUrl(): String = BASE_URL

    fun provideOkhttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()

    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
