package com.example.android.gintastic.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GinTonicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ginTonic: GinTonic)

    @Update
    fun update(ginTonic: GinTonic)

    @Query("SELECT * FROM GinTonic WHERE ginTonicId = :key")
    fun get(key: Long): GinTonic?

    @Query("DELETE FROM GinTonic")
    fun clear()

    @Query("SELECT * FROM GinTonic ORDER BY ginTonicId DESC")
    fun getAllGinTonics(): List<GinTonic>

    @Query("SELECT * FROM GinTonic ORDER BY ginTonicId DESC LIMIT 1")
    fun getNewestGinTonic(): GinTonic?

    @Query("SELECT * FROM GinTonic WHERE is_gin_tonic_favourite = 1 ORDER BY ginTonicId DESC")
    fun getFavouriteGinTonics(): List<GinTonic?>




}