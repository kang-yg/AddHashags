package com.tagplus.addhashtags.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.viewmodel.FragmentMineAddTagViewModel

class FragmentMineAddTagViewModelFactory(private val dataBase: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentMineAddTagViewModel(dataBase) as T
    }
}