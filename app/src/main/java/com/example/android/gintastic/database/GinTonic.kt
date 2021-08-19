package com.example.android.gintastic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GinTonic (

    @PrimaryKey(autoGenerate = true)
    var ginTonicId: Long = 0L,

    @ColumnInfo(name = "gin_tonic_name")
    var name: String = "",

    @ColumnInfo(name = "is_gin_tonic_favourite")
    var favourite: Boolean = false,

    @ColumnInfo(name = "gin_tonic_taste")
    var taste: String ="",

    @ColumnInfo(name = "gin_tonic_description")
    var description: String =""


)