package com.example.android.gintastic.network

import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicProperty

class GinTonicApiHelper(private val service: GinTonicApiService) {
    suspend fun getTrips() = service.getGinTonics()
    suspend fun addGinTonic(ginTonicProperty: GinTonicProperty) = service.addGinTonic(ginTonicProperty)
}