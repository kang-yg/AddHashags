package com.tagplus.addhashtags.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.model.MyTagItem

class FragmentMineTagListViewModel(private val database: AppDatabase) : ViewModel() {
    private val clipLiveData: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun getAllMyTagItems(): List<MyTagItem> {
        return database.myTagItemDAO().gatAllItems()
    }

    fun getClipLiveData(): MutableLiveData<MutableList<String>> = clipLiveData

    fun copyHashTags(clipboardManager: ClipboardManager) {
        val clip: ClipData = ClipData.newPlainText(COPIED_HASHTAGS, createClipData(clipLiveData.value?.toList()))
        clipboardManager.setPrimaryClip(clip)
    }

    private fun createClipData(clipArrayList: List<String>?): String {
        return StringBuilder().apply {
            clipArrayList?.forEach {
                this.append(" $it")
            }
        }.toString()
    }

    companion object {
        private const val COPIED_HASHTAGS = "Copied hashtags"
    }
}