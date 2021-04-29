package com.mx.dcc.rickandmortytest.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mx.dcc.rickandmortytest.R

fun ImageView.loadImageByURL(url: String, context: Context) =
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_broken_image)
        .into(this)