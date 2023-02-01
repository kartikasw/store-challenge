package com.ayodev.store_challenge.util

import android.content.Context
import android.widget.Toast

fun Context.toastLong(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()