package com.example.splashapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.databinding.ActivityPhotoViewerBinding
import com.example.splashapp.ui.photos.PhotosActivity
import com.example.splashapp.utils.loadImage
import com.example.splashapp.utils.viewBinding


class PhotoViewerActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityPhotoViewerBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val photoResponse = intent.getParcelableExtra<PhotoResponse>(PhotosActivity.PHOTO_KEY)

        photoResponse?.urls?.regular?.let { binding.imageActualPhoto.loadImage(it) }
    }
}