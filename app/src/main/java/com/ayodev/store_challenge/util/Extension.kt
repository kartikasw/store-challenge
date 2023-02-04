package com.ayodev.store_challenge.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import com.ayodev.store_challenge.R
import java.io.File
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

fun Context.defaultBitmap(): Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.placeholder_image)

fun String.styledText(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(this)
}

fun Context.toastShort(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toastLong(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}

@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("dd-MM-yyyy")

@SuppressLint("SimpleDateFormat")
val monthAndYearFormat = SimpleDateFormat("MMMM yyyy")

fun getCurrentTime(): Date = Calendar.getInstance().time

fun createCustomTempFile(string: String, context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(string, ".jpg", storageDir)
}