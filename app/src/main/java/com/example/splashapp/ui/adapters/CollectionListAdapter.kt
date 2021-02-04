package com.example.splashapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.splashapp.data.model.CollectionResponse
import com.example.splashapp.databinding.RowItemCollectionBinding
import com.example.splashapp.utils.loadImage

class CollectionListAdapter(private val onItemClicked: (CollectionResponse) -> Unit) :
    ListAdapter<CollectionResponse, CollectionListAdapter.CollectionViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CollectionViewHolder(
        RowItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class CollectionViewHolder(
        private val binding: RowItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionResponse, onItemClicked: (CollectionResponse) -> Unit) {
            binding.textName.text = item?.title
            binding.textCollectionTotal.text = item.total_photos.toString()
            binding.textDescription.text = item?.cover_photo?.description ?: "No description Found"
            binding.imageCover.loadImage(item.cover_photo.urls.thumb)

            binding.root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionResponse>() {
            override fun areItemsTheSame(
                oldItem: CollectionResponse,
                newItem: CollectionResponse
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CollectionResponse,
                newItem: CollectionResponse
            ): Boolean =
                oldItem == newItem
        }
    }
}
