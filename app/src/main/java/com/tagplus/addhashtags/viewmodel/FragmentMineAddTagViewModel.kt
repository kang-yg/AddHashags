package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.TagPlusApplication
import com.tagplus.addhashtags.model.MyTagItem

class FragmentMineAddTagViewModel(private val database: AppDatabase) : ViewModel() {
    private val firebaseDatabase by lazy {
        TagPlusApplication.firebaseDatabase
    }
    private val fcmToken by lazy {
        TagPlusApplication.fcmToken
    }

    fun addTagData(title: String, content: String) {
        val array = content.split(" ").map {
            it.trim()
            if (!it.startsWith("#")) "#".plus(it) else it
        }.filter { it != "#" }
        val contentToString = array.joinToString(separator = " ")
        database.myTagItemDAO().insertMyTagItem(MyTagItem(title, contentToString))
        writeDataToFirebaseRealtimeData(title, contentToString)
    }

    // Firebase write
    // TODO 키(TAG) 랜덤하개 변경
    private fun writeDataToFirebaseRealtimeData(title: String, tags: String) {
        fcmToken?.let { firebaseDatabase.getReference(it).child(title).setValue(title) }
        fcmToken?.let { firebaseDatabase.getReference(it).child(TagPlusApplication.TAGS).setValue(tags) }
    }
}