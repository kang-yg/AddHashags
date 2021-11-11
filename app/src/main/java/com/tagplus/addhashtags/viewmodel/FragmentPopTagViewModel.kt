package com.tagplus.addhashtags.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tagplus.addhashtags.TagPlusApplication

class FragmentPopTagViewModel: ViewModel() {
    var firebaseDatabaseSnapshot: MutableLiveData<HashMap<String, String>> = MutableLiveData()

    private val firebaseDatabase by lazy {
        TagPlusApplication.firebaseDatabase
    }
    private val fcmToken by lazy {
        TagPlusApplication.fcmToken
    }

    // Firebase read
    // TODO 잘못된 데이터를 가져 옴
    fun readDataFromFirebaseRealtimeData() {
        fcmToken?.let {
            firebaseDatabase.getReference(it).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value: HashMap<String, String> = snapshot.value as HashMap<String, String>
                    firebaseDatabaseSnapshot.postValue(value)
                    Log.d("AAA", value.toString())
                }

                // Failed to read value
                override fun onCancelled(error: DatabaseError) {
                }

            })
        }
    }

    // Sort title

    // Sort tags
    fun sortTagsFromFirebaseRealtimeData() {

    }
}