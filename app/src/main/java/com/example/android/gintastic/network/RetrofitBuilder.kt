package com.example.android.gintastic.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "http://192.168.1.18:45456/api/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val apiService: GinTonicApiService by lazy {
        getRetrofit().create(GinTonicApiService::class.java)
    }
}