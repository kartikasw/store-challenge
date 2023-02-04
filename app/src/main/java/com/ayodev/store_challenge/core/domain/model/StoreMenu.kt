package com.ayodev.store_challenge.core.domain.model

data class StoreInfo(
    val title: String,
    val percentage: Int,
    val target: Int,
    val achievement: Int,
    val color: Int
)