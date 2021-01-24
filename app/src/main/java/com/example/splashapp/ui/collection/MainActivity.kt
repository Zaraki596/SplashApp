package com.example.splashapp.ui.collection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.databinding.ActivityMainBinding
import com.example.splashapp.di.Injector
import com.example.splashapp.ui.MainViewModel
import com.example.splashapp.ui.adapters.CollectionListAdapter
import com.example.splashapp.ui.photos.PhotosActivity
import com.example.splashapp.utils.*

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var viewModel: MainViewModel

    private val adapter by lazy { CollectionListAdapter(this::onItemClicked) }
    private var totalPhotosCollection = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = Injector.mainViewModel(this)
        setUpRecylerView()
        observeResponse()

    }

    private fun observeResponse() {
        viewModel.collectionResponse.observe(this) { state ->
            when (state) {
                is State.Loading -> binding.progressHorizontal.show()
                is State.Success -> {
                    adapter.submitList(state.data)
                    state.data.map {
                        totalPhotosCollection += it.total_photos
                    }
                    binding.textCollectionCount.text = totalPhotosCollection.toString()
                    binding.progressHorizontal.hide()
                }
                is State.Error -> {
                    showToast(state.message)
                    binding.progressHorizontal.hide()
                }
            }

        }
    }


    private fun setUpRecylerView() {
        binding.recyclerCollections.adapter = adapter
    }

    private fun onItemClicked(collectionResponse: CollectionResponse) {
        val intent = Intent(this, PhotosActivity::class.java)
        intent.putExtra(COLLECTION_KEY, collectionResponse)
        startActivity(intent)
    }

    companion object {
        const val COLLECTION_KEY = "collection_key"
    }
}