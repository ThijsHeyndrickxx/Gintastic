package com.example.android.gintastic.addgintonic

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.gintastic.allgintonics.AllGinTonicsViewModel
import com.example.android.gintastic.database.GinTonicDao
import java.lang.IllegalArgumentException

class AddGinTonicViewModelFactory(private val dataSource: GinTonicDao,
                                   private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGinTonicViewModel::class.java)){
            return AddGinTonicViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}