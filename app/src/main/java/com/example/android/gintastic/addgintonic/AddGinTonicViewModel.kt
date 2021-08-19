package com.example.android.gintastic.addgintonic

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import com.example.android.gintastic.database.GinTonicProperty
import com.example.android.gintastic.network.RetrofitBuilder
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGinTonicViewModel (val database: GinTonicDao, application: Application):
    AndroidViewModel(application){



    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getTastes(): MutableList<String>{
        return mutableListOf("","Sweet", "Sour", "Mediterranean","Mint", "Other")
    }
    fun addGinTonic(ginTonic: GinTonic){
        uiScope.launch {
            add(ginTonic)
        }
    }
    private suspend fun add(ginTonic: GinTonic){
        withContext(Dispatchers.IO){
            RetrofitBuilder.apiService.addGinTonic(convertToGinTonicProperty(ginTonic)).enqueue(object: Callback<GinTonicProperty> {
                override fun onResponse(call: Call<GinTonicProperty>, response: Response<GinTonicProperty>) {
                    database.insert(ginTonic)
                }
                override fun onFailure(call: Call<GinTonicProperty>, t: Throwable) {

                }
            })
        }
    }

    private fun convertToGinTonicProperty(ginTonic: GinTonic): GinTonicProperty {
        var gtProperty = GinTonicProperty(
            ginTonic.ginTonicId.toInt(),ginTonic.name,ginTonic.taste,ginTonic.description
        )
        return gtProperty

    }









}