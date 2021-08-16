package com.example.android.gintastic.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GinTonicDao {
    @Insert
    fun insert(ginTonic: GinTonic)

    @Update
    fun update(ginTonic: GinTonic)

    @Query("SELECT * FROM GinTonic WHERE ginTonicId = :key")
    fun get(key: Long): GinTonic?

    @Query("DELETE FROM GinTonic")
    fun clear()

    @Query("SELECT * FROM GinTonic ORDER BY ginTonicId DESC")
    fun getAllGinTonics(): LiveData<List<GinTonic>>

    @Query("SELECT * FROM GinTonic ORDER BY ginTonicId DESC LIMIT 1")
    fun getNewestGinTonic(): GinTonic?


}