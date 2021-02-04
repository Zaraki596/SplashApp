package com.example.splashapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.data.repo.UnsplashRepository
import com.example.splashapp.utils.State
import com.example.splashapp.utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel(val app: Application, val unsplashRepository: UnsplashRepository) :
    AndroidViewModel(app) {

    private val _collectionResponse = MutableLiveData<State<List<CollectionResponse>>>()
    val collectionResponse: LiveData<State<List<CollectionResponse>>> get() = _collectionResponse

    private val _photosResponse = MutableLiveData<State<List<PhotoResponse>>>()
    val photosResponse: LiveData<State<List<PhotoResponse>>> get() = _photosResponse

    var pageNumber = 1

    init {
        getCollection()
    }

    fun getCollection() = viewModelScope.launch {
        safeCollections()
    }

    private fun handleCollectionResponse(response: Response<List<CollectionResponse>>): State<List<CollectionResponse>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return State.success(it)
            }
        }
        return State.error(response.message())
    }

    fun getPhotos(id: String) = viewModelScope.launch {
        safePhotos(id)
    }

    private fun handlePhotosResponse(response: Response<List<PhotoResponse>>): State<List<PhotoResponse>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return State.success(it)
            }
        }
        return State.error(response.message())
    }

    private suspend fun safeCollections() {
        _collectionResponse.postValue(State.Loading())
        try {
            if (hasInternetConnection(app.applicationContext)) {
                val response = unsplashRepository.getCollection(pageNumber)
                _collectionResponse.postValue(handleCollectionResponse(response))
            } else {
                _collectionResponse.postValue(State.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _collectionResponse.postValue(State.Error("Network Failure"))
                else -> _collectionResponse.postValue(State.Error("Conversion Error"))
            }
        }
    }

    private suspend fun safePhotos(id: String) {
        _photosResponse.postValue(State.Loading())
        try {
            if (hasInternetConnection(app.applicationContext)) {
                val response = unsplashRepository.getPhotos(id)
                _photosResponse.postValue(handlePhotosResponse(response))
            } else {
                _photosResponse.postValue(State.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _photosResponse.postValue(State.Error("Network Failure"))
                else -> _photosResponse.postValue(State.Error("Conversion Error"))
            }
        }
    }
}
