package com.example.splashapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splashapp.data.model.PhotoResponse
import com.example.splashapp.databinding.RowItemPhotosBinding
import com.example.splashapp.utils.loadImage

class PhotosListAdapter(private val onItemClicked: (PhotoResponse) -> Unit) :
    ListAdapter<PhotoResponse, PhotosListAdapter.PhotosViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotosViewHolder(
        RowItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class PhotosViewHolder(
        private val binding: RowItemPhotosBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoResponse, onItemClicked: (PhotoResponse) -> Unit) {
            item.urls?.regular?.let { binding.imagePhotos.loadImage(it) }

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PhotoResponse>() {
            override fun areItemsTheSame(
                oldItem: PhotoResponse,
                newItem: PhotoResponse
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PhotoResponse,
                newItem: PhotoResponse
            ): Boolean =
                oldItem == newItem
        }
    }
}
