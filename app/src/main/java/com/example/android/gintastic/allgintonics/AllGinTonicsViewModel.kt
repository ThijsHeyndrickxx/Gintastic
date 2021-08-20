package com.example.android.gintastic.allgintonics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.gintastic.network.RetrofitBuilder
import com.example.android.gintastic.database.GinTonic
import com.example.android.gintastic.database.GinTonicDao
import com.example.android.gintastic.database.GinTonicProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllGinTonicsViewModel (val database: GinTonicDao, application: Application): AndroidViewModel(application)
{
    private val _ginTonics = MutableLiveData<List<GinTonic>>()
    val ginTonics: LiveData<List<GinTonic>>
        get() = _ginTonics
    val newestGinTonic = MutableLiveData<GinTonic?>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    init{
        initializeGinTonics()

    }

    private fun initializeGinTonics() {
        uiScope.launch {
            var deferredProperties = RetrofitBuilder.apiService.getGinTonics()
            try {
                var ginTonicProperties = deferredProperties.await()
                var gts = mutableListOf<GinTonic>()
                ginTonicProperties.forEach {
                    gts.add(GinTonic(it.id.toLong(), it.name, false, it.taste, it.description))
                }
                _ginTonics.value = gts
                cacheGinTonics(gts)
            } catch (t: Throwable) {
                _ginTonics.value = database.getAllGinTonics()
            }

        }
    }




    private fun cacheGinTonics(gts: MutableList<GinTonic>) {
        gts.forEach {
            database.insert(it)
        }

    }


    }
