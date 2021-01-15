package com.example.splashapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.data.repo.UnsplashRepository
import com.example.splashapp.utils.State
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val unsplashRepository: UnsplashRepository) : ViewModel() {


    private val _collectionResponse = MutableLiveData<State<List<CollectionResponse>>>()
    val collectionResponse: LiveData<State<List<CollectionResponse>>> get() = _collectionResponse

    private val _photosResponse = MutableLiveData<State<List<PhotoResponse>>>()
    val photosResponse: LiveData<State<List<PhotoResponse>>> get() = _photosResponse

    var pageNumber = 1

    init {
        getCollection()
    }

    fun getCollection() = viewModelScope.launch {
        _collectionResponse.postValue(State.loading())

        val response = unsplashRepository.getCollection(pageNumber)
        _collectionResponse.postValue(handleCollectionResponse(response))


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
        _photosResponse.postValue(State.loading())

        val response = unsplashRepository.getPhotos(id)
        _photosResponse.postValue(handlePhotosResponse(response))

    }

    private fun handlePhotosResponse(response: Response<List<PhotoResponse>>): State<List<PhotoResponse>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return State.success(it)
            }
        }
        return State.error(response.message())
    }
}