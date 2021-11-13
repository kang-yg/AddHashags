package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tagplus.addhashtags.TagPlusApplication

class FragmentPopTagViewModel : ViewModel() {
    var tagSortedCountMap: Map<String, Int> = mapOf()
    private val tagCountMap: HashMap<String, Int> = HashMap()
    private val allTagArrayList: ArrayList<String> = arrayListOf()
    private val firebaseDatabase by lazy {
        TagPlusApplication.firebaseDatabase
    }

    // Firebase read
    fun readDataFromFirebaseRealtimeData(invalidateChipGroup: () -> Unit) {
        firebaseDatabase.getReference(TagPlusApplication.USER_ID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                parseFirebaseDataSnapshot(snapshot)
                setTagUsageFrequency()
                sortTagsFromFirebaseRealtimeData()
                invalidateChipGroup()
            }

            // Failed to read value
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    // Sort tags
    private fun sortTagsFromFirebaseRealtimeData() {
        tagSortedCountMap = tagCountMap.toList().sortedBy { (_, value) -> value }.reversed().toMap()
    }

    private fun setTagUsageFrequency() {
        val allTagString = allTagArrayList.joinToString(" ")
        allTagString.split(" ").filter { it != "" }.forEach { tag ->
            val count: Int = tagCountMap[tag] ?: 0
            tagCountMap[tag] = count
            if (tagCountMap.containsKey(tag)) {
                tagCountMap[tag] = count + 1
            }
        }
    }

    private fun parseFirebaseDataSnapshot(snapshot: DataSnapshot) {
        allTagArrayList.clear()
        for (underUserKey: DataSnapshot in snapshot.children) {
            for (underTitle: DataSnapshot in underUserKey.children) {
                val tag = underTitle.child(TagPlusApplication.TAGS)
                if (tag.value != null) {
                    allTagArrayList.add(tag.value.toString())
                }
            }
        }
    }
}