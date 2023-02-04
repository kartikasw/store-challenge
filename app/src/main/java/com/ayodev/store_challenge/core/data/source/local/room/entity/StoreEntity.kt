package com.ayodev.store_challenge.core.data.source.local.room.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayodev.store_challenge.core.util.Constants.ACCOUNT_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.ACCOUNT_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.ADDRESS_COLUMN
import com.ayodev.store_challenge.core.util.Constants.AREA_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.AREA_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.CHANNEL_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.CHANNEL_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.DC_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.DC_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.IMAGE_COLUMN
import com.ayodev.store_challenge.core.util.Constants.LATITUDE_COLUMN
import com.ayodev.store_challenge.core.util.Constants.LONGITUDE_COLUMN
import com.ayodev.store_challenge.core.util.Constants.REGION_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.REGION_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.STORE_CODE_COLUMN
import com.ayodev.store_challenge.core.util.Constants.STORE_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.STORE_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.STORE_TABLE
import com.ayodev.store_challenge.core.util.Constants.SUBCHANNEL_ID_COLUMN
import com.ayodev.store_challenge.core.util.Constants.SUBCHANNEL_NAME_COLUMN
import com.ayodev.store_challenge.core.util.Constants.VISIT_COLUMN
import com.ayodev.store_challenge.core.util.Constants.VISIT_DATE_COLUMN
import java.util.*
import javax.annotation.Nullable

@Entity(tableName = STORE_TABLE)
data class StoreEntity(
    @ColumnInfo(name = STORE_ID_COLUMN)
    val store_id: Int = 0,

    @ColumnInfo(name = STORE_CODE_COLUMN)
    val store_code: String = "",

    @ColumnInfo(name = STORE_NAME_COLUMN)
    val store_name: String = "",

    @ColumnInfo(name = ADDRESS_COLUMN)
    val address: String = "",

    @ColumnInfo(name = DC_ID_COLUMN)
    val dc_id: Int = 0,

    @ColumnInfo(name = DC_NAME_COLUMN)
    val dc_name: String = "",

    @ColumnInfo(name = ACCOUNT_ID_COLUMN)
    val account_id: Int = 0,

    @ColumnInfo(name = ACCOUNT_NAME_COLUMN)
    val account_name: String = "",

    @ColumnInfo(name = SUBCHANNEL_ID_COLUMN)
    val subchannel_id: Int = 0,

    @ColumnInfo(name = SUBCHANNEL_NAME_COLUMN)
    val subchannel_name: String = "",

    @ColumnInfo(name = CHANNEL_ID_COLUMN)
    val channel_id: Int = 0,

    @ColumnInfo(name = CHANNEL_NAME_COLUMN)
    val channel_name: String = "",

    @ColumnInfo(name = AREA_ID_COLUMN)
    val area_id: Int = 0,

    @ColumnInfo(name = AREA_NAME_COLUMN)
    val area_name: String = "",

    @ColumnInfo(name = REGION_ID_COLUMN)
    val region_id: Int = 0,

    @ColumnInfo(name = REGION_NAME_COLUMN)
    val region_name: String = "",

    @ColumnInfo(name = LATITUDE_COLUMN)
    val latitude: Double = 0.0,

    @ColumnInfo(name = LONGITUDE_COLUMN)
    val longitude: Double = 0.0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN)
    val id: Int = 0,

    @ColumnInfo(name = VISIT_COLUMN)
    var visit: Boolean = false,

    @ColumnInfo(name = VISIT_DATE_COLUMN)
    var visit_date: Date = Date(),

    @Nullable
    @ColumnInfo(name = IMAGE_COLUMN)
    var image: Bitmap? = null
)