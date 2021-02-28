package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.model.MyTagItem
import io.reactivex.schedulers.Schedulers

class FragmentMineAddTagViewModel(private val database: AppDatabase) : ViewModel() {
    fun addTagData(title: String, content: String) {
        val array = content.split(" ").map {
            it.trim()
            if (!it.startsWith("#")) "#".plus(it) else it
        }
        val contentToString = array.joinToString(separator = " ")
        database.myTagItemDAO().insertMyTagItem(MyTagItem(title, contentToString)).subscribeOn(Schedulers.io()).subscribe()
    }
}