package com.example.android.gintastic.detailedgintonic

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import kotlinx.coroutines.*

class DetailedGinTonicViewModel(val database: GinTonicDao, application: Application, ginTonicId: Long) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    val ginTonicId: Long = ginTonicId
    val currentGinTonic= MutableLiveData<GinTonic>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init{
        initializeCurrentGinTonic()
    }

    fun toggleFavourite(isChecked: Boolean){
        uiScope.launch {
            toggleFavourites(isChecked)
        }
    }
    private suspend fun toggleFavourites(isChecked: Boolean) {
        withContext(Dispatchers.IO){
            currentGinTonic.value!!.favourite = !isChecked
            database.update(currentGinTonic.value!!)
        }

    }


    private fun initializeCurrentGinTonic() {
        uiScope.launch {
            currentGinTonic.value = getCurrentGinTonicFromDatabase(ginTonicId)
        }
    }

    private suspend fun getCurrentGinTonicFromDatabase(ginTonicId: Long): GinTonic? {
        return withContext(Dispatchers.IO){
            var currentGT = database.get(ginTonicId)
            currentGT
        }

    }

}