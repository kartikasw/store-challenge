package com.ayodev.store_challenge.core.data.source.local.room.dao

import android.graphics.Bitmap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayodev.store_challenge.core.data.source.local.room.entity.StoreEntity
import com.ayodev.store_challenge.core.util.Constants.STORE_TABLE
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface StoreDao {
    @Query("SELECT * from $STORE_TABLE")
    fun selectAllStore(): Flow<List<StoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStore(storeEntity: List<StoreEntity>)

    @Query("UPDATE $STORE_TABLE SET visit = :visit, visit_date = :visit_date WHERE id = :id")
    suspend fun updateStoreWhenVisit(id: Int, visit: Boolean, visit_date: Date)
}