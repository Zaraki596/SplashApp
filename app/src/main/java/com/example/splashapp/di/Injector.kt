package com.example.splashapp.di

import androidx.appcompat.app.AppCompatActivity
import com.example.splashapp.ui.MainViewModel

object Injector {

    /**
     * Manual Dependency injection to pass the required parameter
     * */

    /*
    * handle All network related dependencies*/
    private val loggingInterceptor = NetworkResolver.provideLoggingInterceptor()

    private val okHttp = NetworkResolver.provideOkhttpClient(loggingInterceptor)

    private val baseUrl = NetworkResolver.provideBaseUrl()

    private val retrofit = NetworkResolver.provideRetrofitClient(
        baseUrl,
        okHttp
    )

    private val apiManager = NetworkResolver.provideApiManager(retrofit)

    /*
    * handle All Application related dependencies*/
    private val remoteDataSource by lazy {
        AppResolver.provieRemoteDataSource(apiManager)
    }

    private val unSplashRepository by lazy {
        AppResolver.provideUnsplashRepository(remoteDataSource)
    }

    /*
    * handle All ViewModel related dependencies*/
    private val mainViewModelFactory by lazy {
        ViewModelResolver.provideMainViewModelFactory(unSplashRepository)
    }

    fun mainViewModel(activity: AppCompatActivity): MainViewModel {
        return ViewModelResolver.provideMainViewModel(mainViewModelFactory, activity)
    }
}
