package com.example.android.gintastic.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GinTonic (

    @PrimaryKey(autoGenerate = true)
    var ginTonicId: Long = 0L,

    @ColumnInfo(name = "gin_tonic_name")
    var name: String = ""

)