package com.tagplus.addhashtags.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tagplus.addhashtags.TagPlusApplication

class FragmentPopTagViewModel : ViewModel() {
    private val allTagArrayList: ArrayList<String> = arrayListOf()
    val tagCountMap: HashMap<String, Int> = HashMap()

    private val firebaseDatabase by lazy {
        TagPlusApplication.firebaseDatabase
    }
    private val fcmToken by lazy {
        TagPlusApplication.fcmToken
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
        tagCountMap.toList().sortedBy { (_, value) -> value }.reversed().toMap()
    }

    private fun setTagUsageFrequency() {
        tagCountMap.clear()
        val allTagString = allTagArrayList.joinToString(" ")
        allTagString.split(" ").forEach { tag ->
            var count = 1
            if (tagCountMap.containsKey(tag)) {
                count++
            }
            tagCountMap[tag] = count
        }
    }

    private fun parseFirebaseDataSnapshot(snapshot: DataSnapshot) {
        for (underUserKey: DataSnapshot in snapshot.children) {
            for (underTitle: DataSnapshot in underUserKey.children) {
                val tag = underTitle.child(TagPlusApplication.TAGS)
                allTagArrayList.add(tag.value.toString())
            }
        }
    }
}