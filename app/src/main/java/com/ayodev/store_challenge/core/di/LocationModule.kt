package com.ayodev.store_challenge.core.di

import android.app.Application
import android.location.Geocoder
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    @Singleton
    fun provideLocationProviderClient(application: Application) =
        LocationServices.getFusedLocationProviderClient(application)

}