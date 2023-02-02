package com.ayodev.store_challenge.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayodev.store_challenge.core.data.source.local.room.converter.DateConverter
import com.ayodev.store_challenge.core.data.source.local.room.dao.StoreDao
import com.ayodev.store_challenge.core.data.source.local.room.entity.StoreEntity

@Database(
    entities = [StoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class StoreDatabase: RoomDatabase() {
    abstract fun storeDao(): StoreDao
}