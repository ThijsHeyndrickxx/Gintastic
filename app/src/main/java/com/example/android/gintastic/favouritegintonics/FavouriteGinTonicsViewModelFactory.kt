package com.example.android.gintastic.favouritegintonics

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.gintastic.database.GinTonicDao
import com.example.android.gintastic.detailedgintonic.DetailedGinTonicViewModel
import java.lang.IllegalArgumentException

class FavouriteGinTonicsViewModelFactory(private val dataSource: GinTonicDao,
                                       private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteGinTonicsViewModel::class.java)){
            return FavouriteGinTonicsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}