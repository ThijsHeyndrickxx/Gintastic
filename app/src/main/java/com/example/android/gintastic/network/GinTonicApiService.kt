package com.example.android.gintastic.network

import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicProperty
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface GinTonicApiService {

    @GET("GinTonics")
     fun getGinTonics(): Deferred<List<GinTonicProperty>>

    @POST("GinTonics")
     fun addGinTonic(@Body ginTonicProperty: GinTonicProperty): Deferred<GinTonicProperty>
}