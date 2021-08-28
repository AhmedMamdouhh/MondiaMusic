package com.mondia.manager.utilities

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import java.net.URL
import kotlin.concurrent.thread


@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {

    if(this.drawable == null) {

        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build().toString()


            val uiHandler = Handler(Looper.getMainLooper())
            thread(start = true) {
                val bitmap = downloadBitmap(imgUri)
                uiHandler.post {
                    this.setImageBitmap(bitmap)
                }
            }
        }
    }

}

fun downloadBitmap(imageUrl: String): Bitmap? {
    return try {
        val conn = URL(imageUrl).openConnection()
        conn.connect()
        val inputStream = conn.getInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        bitmap
    } catch (e: Exception) {
        Log.e(ContentValues.TAG, "Exception $e")
        null
    }
}
