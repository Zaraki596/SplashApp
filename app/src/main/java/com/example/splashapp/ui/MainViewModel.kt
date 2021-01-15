package com.example.splashapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.repo.UnsplashRepository
import com.example.splashapp.utils.State
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val unsplashRepository: UnsplashRepository) : ViewModel() {


    private val _collectionResponse = MutableLiveData<State<List<CollectionResponse>>>()
    val collectionResponse: LiveData<State<List<CollectionResponse>>> get() = _collectionResponse


    var pageNumber = 1


    fun getCollection() = viewModelScope.launch {
        _collectionResponse.postValue(State.loading())

        val response = unsplashRepository.getCollection(pageNumber)
        _collectionResponse.postValue(handlerCollectionResponse(response))


    }

    private fun handlerCollectionResponse(response: Response<List<CollectionResponse>>): State<List<CollectionResponse>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return State.success(it)
            }
        }
        return State.error(response.message())
    }

}