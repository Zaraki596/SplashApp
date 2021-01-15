package com.example.splashapp.utils

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.splashapp.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(CircularProgressDrawable(this.context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        })
        .error(R.drawable.ic_error)
        .into(this)
}