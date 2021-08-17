package com.example.android.gintastic.detailedgintonic

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.gintastic.addgintonic.AddGinTonicViewModel
import com.example.android.gintastic.database.GinTonicDao
import java.lang.IllegalArgumentException

class DetailedGinTonicViewModelFactory(private val dataSource: GinTonicDao,
                                       private val application: Application,
                                       private val ginTonicId: Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedGinTonicViewModel::class.java)){
            return DetailedGinTonicViewModel(dataSource, application, ginTonicId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}