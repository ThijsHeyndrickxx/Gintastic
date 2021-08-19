package com.example.android.gintastic.database

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GinTonicProperty(
    var id: Int,
    var name: String,
    var taste: String,
    var description: String
)
