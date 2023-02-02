package com.ayodev.store_challenge.core.data.source.local

import com.ayodev.store_challenge.core.data.source.local.room.dao.StoreDao
import com.ayodev.store_challenge.core.data.source.local.room.entity.StoreEntity
import com.ayodev.store_challenge.core.data.source.local.shared_pref.StorePreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val store: StoreDao,
    private val pref: StorePreference
) {
    fun selectAllStore(): Flow<List<StoreEntity>> =
        store.selectAllStore()

    suspend fun insertAllStore(stores: List<StoreEntity>): Unit =
        store.insertAllStore(stores)

    fun setLoginStatus(status: Boolean) =
        pref.setLoginStatus(status)

    fun getLoginStatus() =
        pref.getLoginStatus()
}