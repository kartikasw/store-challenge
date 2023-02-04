package com.ayodev.store_challenge.core.data.source.local.room.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class BitmapConverter {

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 10, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? =
        byteArray?.size?.let { BitmapFactory.decodeByteArray(byteArray, 0, it) }


}