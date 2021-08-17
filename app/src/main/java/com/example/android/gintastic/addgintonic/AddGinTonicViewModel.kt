package com.example.android.gintastic.addgintonic

import android.app.Application
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import kotlinx.coroutines.*

class AddGinTonicViewModel (val database: GinTonicDao, application: Application):
    AndroidViewModel(application){

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun addGinTonic(ginTonic: GinTonic){
        uiScope.launch {
            add(ginTonic)
        }
    }
    private suspend fun clear(){
        database.clear()
    }
    private suspend fun add(ginTonic: GinTonic){
        withContext(Dispatchers.IO){
            database.insert(ginTonic)
        }

    }





}