package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.model.MyTagItem

class FragmentMineTagListViewModel(private val database: AppDatabase) : ViewModel() {
    fun getAllMyTagItems(): List<MyTagItem> {
        return database.myTagItemDAO().gatAllItems()
    }
}