package com.example.android.gintastic

import android.app.Instrumentation
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.gintastic.database.AppDatabase
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GinTonicDatabaseTest {
    private lateinit var ginTonicDao: GinTonicDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        ginTonicDao = db.ginTonicDao
    }
    @After
    @Throws(Exception::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetGinTonic() {
        val ginTonic = GinTonic()
        ginTonicDao.insert(ginTonic)
        val ginTonics = ginTonicDao.getAllGinTonics()
        assertEquals(ginTonics.size, 1)
    }
    @Test
    @Throws(Exception::class)
    fun insertAndGetFavouriteGinTonic() {
        val ginTonicNotFavourite = GinTonic(ginTonicId = 1)
        val ginTonicFavourite = GinTonic(ginTonicId = 2,favourite = true)
        ginTonicDao.insert(ginTonicNotFavourite)
        ginTonicDao.insert(ginTonicFavourite)
        val favouriteGinTonics = ginTonicDao.getFavouriteGinTonics()
        assertEquals(favouriteGinTonics.size, 1)
        assertTrue(favouriteGinTonics[0]!!.ginTonicId.toInt() == 2)

    }
}