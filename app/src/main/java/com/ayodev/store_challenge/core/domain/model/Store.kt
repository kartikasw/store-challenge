package com.ayodev.store_challenge.core.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class Store(
    val store_id: Int = 0,
    val store_code: String = "",
    val store_name: String = "",
    val address: String = "",
    val dc_id: Int = 0,
    val dc_name: String = "",
    val account_id: Int = 0,
    val account_name: String = "",
    val subchannel_id: Int = 0,
    val subchannel_name: String = "",
    val channel_id: Int = 0,
    val channel_name: String = "",
    val area_id: Int = 0,
    val area_name: String = "",
    val region_id: Int = 0,
    val region_name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val id: Int = 0,
    var visit: Boolean = false,
    var visit_date: Date = Date(),
    var distance: Double = 0.0,
    var image: Bitmap?
): Parcelable