package com.example.android.gintastic.allgintonics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import kotlinx.coroutines.*

class AllGinTonicsViewModel (val database: GinTonicDao, application: Application): AndroidViewModel(application)
{
    private val ginTonics = database.getAllGinTonics()
    val newestGinTonic = MutableLiveData<GinTonic?>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init{
        initializeNewestGinTonic()
    }

    private fun initializeNewestGinTonic() {
        uiScope.launch {
            newestGinTonic.value = getNewestGTFromDatabase()
        }
    }

    private suspend fun getNewestGTFromDatabase(): GinTonic? {

        return withContext(Dispatchers.IO) {
            var ginTonic = database.getNewestGinTonic()
            ginTonic
        }
    }

    }
