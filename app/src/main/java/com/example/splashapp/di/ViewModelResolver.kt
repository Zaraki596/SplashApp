package com.example.splashapp.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.splashapp.data.repo.UnsplashRepository
import com.example.splashapp.ui.MainViewModel
import com.example.splashapp.ui.MainViewModelFactory

object ViewModelResolver {

    fun provideMainViewModel(
        factory: MainViewModelFactory,
        activity: AppCompatActivity
    ): MainViewModel = ViewModelProvider(activity, factory).get(MainViewModel::class.java)

    fun provideMainViewModelFactory(unSplashRepository: UnsplashRepository) =
        MainViewModelFactory(unSplashRepository)

}
