package com.ayodev.store_challenge.core.data.source.local

import com.ayodev.store_challenge.core.data.source.local.room.dao.StoreDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val store: StoreDao) {
}