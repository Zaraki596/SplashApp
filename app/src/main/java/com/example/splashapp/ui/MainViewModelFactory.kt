package com.example.splashapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.splashapp.data.repo.UnsplashRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    val app: Application,
    private val unSplashRepository: UnsplashRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(app, unSplashRepository) as T
        }
        throw IllegalArgumentException("ViewModel Not found")
    }
}
