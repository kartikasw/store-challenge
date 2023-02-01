package com.ayodev.store_challenge.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayodev.store_challenge.core.data.source.local.room.dao.StoreDao

@Database(entities = [], exportSchema = false, version = 1)
abstract class StoreDatabase: RoomDatabase() {
    abstract fun storeDao(): StoreDao
}