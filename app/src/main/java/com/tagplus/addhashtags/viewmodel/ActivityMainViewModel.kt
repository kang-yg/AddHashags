package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.model.MyTagItem
import io.reactivex.schedulers.Schedulers

class ActivityMainViewModel(private val database: AppDatabase) : ViewModel() {
    var testArray = MutableLiveData<ArrayList<MyTagItem>>()
    init {
        getAllMyTagItems()
    }

    fun getAllMyTagItems() {
        database.myTagItemDAO().gatAllItems().subscribeOn(Schedulers.io()).subscribe { it ->
            this.testArray.postValue(it as ArrayList<MyTagItem>)
        }
    }
}