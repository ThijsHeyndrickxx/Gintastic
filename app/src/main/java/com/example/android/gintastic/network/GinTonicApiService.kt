package com.example.android.gintastic.network

import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicProperty
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface GinTonicApiService {

    @GET("GinTonics")
     fun getGinTonics(): Call<List<GinTonicProperty>>

    @POST("GinTonics")
    suspend fun addGinTonic(@Body ginTonicProperty: GinTonicProperty)
}