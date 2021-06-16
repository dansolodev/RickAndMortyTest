package com.mx.dcc.rickandmortytest.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mx.dcc.rickandmortytest.R

fun ImageView.loadImageByURL(url: String, context: Context) =
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.ic_broken_image)
        .into(this)

fun ImageView.loadCircleImageByURL(url: String, context: Context) =
    Glide.with(context)
        .load(url)
        .centerCrop()
        .apply(RequestOptions.circleCropTransform())
        .error(R.drawable.ic_broken_image)
        .into(this)

fun Context.showMessageToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()