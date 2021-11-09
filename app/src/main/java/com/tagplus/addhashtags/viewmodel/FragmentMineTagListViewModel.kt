package com.tagplus.addhashtags.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.model.MyTagItem

class FragmentMineTagListViewModel(private val database: AppDatabase) : ViewModel() {
    private var clipDataList: ArrayList<String> = arrayListOf()

    fun getAllMyTagItems(): List<MyTagItem> {
        return database.myTagItemDAO().gatAllItems()
    }

    fun getClipDataList(): ArrayList<String> {
        return this.clipDataList
    }

    fun copyHashTags(clipboardManager: ClipboardManager) {
        val clip: ClipData = ClipData.newPlainText(COPIED_HASHTAGS, createClipData(clipDataList))
        clipboardManager.setPrimaryClip(clip)
    }

    private fun createClipData(clipArrayList: List<String>?): String {
        return StringBuilder().apply {
            clipArrayList?.forEach {
                this.append(" $it")
            }
        }.toString()
    }

    fun deleteTagData(myTagItem: MyTagItem) {
        database.myTagItemDAO().deleteItem(myTagItem)
    }

    companion object {
        private const val COPIED_HASHTAGS = "Copied hashtags"
    }
}