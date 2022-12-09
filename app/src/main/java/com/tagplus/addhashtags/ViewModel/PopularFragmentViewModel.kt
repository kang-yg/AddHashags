package com.tagplus.addhashtags.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tagplus.addhashtags.Model.HashTag
import com.tagplus.addhashtags.Model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _popularHashTagsLiveData: MutableLiveData<List<HashTag>> = MutableLiveData()
    val popularHashTagsLiveData: LiveData<List<HashTag>>
        get() = _popularHashTagsLiveData

    private fun getPopularTags() {
        repository.getDatabaseReference().addValueEventListener(getValueEventListener())
    }

    private fun getValueEventListener() = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val a = snapshot.value
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

    init {
        getPopularTags()
    }
}