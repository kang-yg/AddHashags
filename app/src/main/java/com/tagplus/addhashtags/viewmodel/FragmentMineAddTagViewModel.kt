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
    private fun writeDataToFirebaseRealtimeData(title: String, tags: String) {
        fcmToken?.let { firebaseDatabase.getReference(TagPlusApplication.USER_ID).child(it).child(title).setValue(title) }
        fcmToken?.let { firebaseDatabase.getReference(TagPlusApplication.USER_ID).child(it).child(title).child(TagPlusApplication.TAGS).setValue(tags) }
    }
}