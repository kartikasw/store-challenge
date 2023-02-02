package com.ayodev.store_challenge.core.data.mapper

import com.ayodev.store_challenge.core.data.source.local.room.entity.StoreEntity
import com.ayodev.store_challenge.core.data.source.remote.response.StoreResponse
import com.ayodev.store_challenge.core.domain.model.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun StoreResponse.toEntity(): StoreEntity =
    StoreEntity(
        store_id,
        store_code,
        store_name,
        address,
        dc_id,
        dc_name,
        account_id,
        account_name,
        subchannel_id,
        subchannel_name,
        channel_id,
        channel_name,
        area_id,
        area_name,
        region_id,
        region_name,
        latitude,
        longitude
    )

fun StoreEntity.toModel(): Store =
    Store(
        store_id,
        store_code,
        store_name,
        address,
        dc_id,
        dc_name,
        account_id,
        account_name,
        subchannel_id,
        subchannel_name,
        channel_id,
        channel_name,
        area_id,
        area_name,
        region_id,
        region_name,
        latitude,
        longitude,
        id,
        visit,
        visit_date
    )

fun List<StoreEntity>.toListModel(): List<Store> =
    this.map { it.toModel() }

fun List<StoreResponse>.toListEntity(): List<StoreEntity> =
    this.map { it.toEntity() }

fun Flow<List<StoreEntity>>.toListFlowModel(): Flow<List<Store>> =
    this.map { it.toListModel() }