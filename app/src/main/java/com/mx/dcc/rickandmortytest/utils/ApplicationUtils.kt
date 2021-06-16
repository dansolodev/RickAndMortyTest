package com.mx.dcc.rickandmortytest.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ApplicationUtils {

    fun getBitmapFromURL(imageUrl: String?): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection? = url.openConnection() as? HttpURLConnection
            connection?.doInput = true
            connection?.connect()
            val input = connection?.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}