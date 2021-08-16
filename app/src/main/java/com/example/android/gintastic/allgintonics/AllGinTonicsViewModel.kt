package com.example.android.gintastic.allgintonics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.gintastic.database.GinTonicDao

class AllGinTonicsViewModel (val database: GinTonicDao, application: Application): AndroidViewModel(application)
{
}