package com.example.splashapp.ui.photos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.splashapp.R
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.databinding.ActivityPhotosBinding
import com.example.splashapp.di.Injector
import com.example.splashapp.ui.MainViewModel
import com.example.splashapp.ui.PhotoViewerActivity
import com.example.splashapp.ui.adapters.PhotosListAdapter
import com.example.splashapp.ui.collection.MainActivity
import com.example.splashapp.utils.*


class PhotosActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityPhotosBinding::inflate)

    private lateinit var viewModel: MainViewModel

    private val adapter by lazy { PhotosListAdapter(this::onItemClicked) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()

        //Recieved the value from the previous activity
        val collectionResponse =
            intent.getParcelableExtra<CollectionResponse>(MainActivity.COLLECTION_KEY)


        //Initial setup for this screen using above data
        binding.textToolbarTitle.text = collectionResponse?.title
        binding.textToolbarCount.text = collectionResponse?.total_photos.toString()
        binding.textCollectionLink.apply {
            text = collectionResponse?.links?.html
            setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    this.data = Uri.parse(collectionResponse?.links?.html)
                    startActivity(this)
                }
            }
        }

        viewModel = Injector.mainViewModel(this)

        setUpRecylerView()
        getCollectionPhotos(collectionResponse?.id)
        observeResponse()
    }


    private fun getCollectionPhotos(id: String?) {
        viewModel.getPhotos(id!!)
    }


    private fun observeResponse() {
        viewModel.photosResponse.observe(this) { state ->
            when (state) {
                is State.Loading -> binding.progressHorizontal.show()
                is State.Success -> {
                    adapter.submitList(state.data.take(6))
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
        binding.recyclerPhotos.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.navigationIcon?.mutate()?.let {
            it.setTint(ContextCompat.getColor(this, R.color.design_default_color_secondary))
            binding.toolbar.navigationIcon = it
        }
    }

    private fun onItemClicked(photoResponse: PhotoResponse) {
        val intent = Intent(this, PhotoViewerActivity::class.java)
        intent.putExtra(PHOTO_KEY, photoResponse)
        startActivity(intent)
    }

    companion object {
        const val PHOTO_KEY = "photo_key"
    }

}