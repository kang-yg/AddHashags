package com.tagplus.addhashtags.view.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.viewmodel.ActivityMainViewModel

class ActivityMainViewModelFactory(private val dataBase: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActivityMainViewModel(dataBase) as T
    }
}