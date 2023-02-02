package com.ayodev.store_challenge.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat

fun Context.toastLong(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

val dateFormat = SimpleDateFormat("yyyy-MM-dd")
