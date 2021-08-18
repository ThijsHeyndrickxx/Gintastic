package com.example.android.gintastic.favouritegintonics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import kotlinx.coroutines.*

class FavouriteGinTonicsViewModel(val database: GinTonicDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val favouriteGinTonics = MutableLiveData<List<GinTonic?>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init{
        initializeFavouriteGinTonics()
    }

    private fun initializeFavouriteGinTonics() {
        uiScope.launch {
            favouriteGinTonics.value = getFavouriteGinTonicsFromDatabase()
        }
    }

    private suspend fun getFavouriteGinTonicsFromDatabase(): List<GinTonic?> {
        return withContext(Dispatchers.IO){
            var favGTs = database.getFavouriteGinTonics()
            favGTs
        }
    }
}