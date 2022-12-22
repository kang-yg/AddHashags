package com.tagplus.addhashtags.ViewModel

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
            _popularHashTagsLiveData.postValue(getTagsFromSnapshot(snapshot))
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

    private fun getTagsFromSnapshot(snapshot: DataSnapshot): List<HashTag> {
        val result = ArrayList<HashTag>()
        snapshot.children.forEach { root ->
            root.children.forEach { userKey ->
                userKey.children.forEach { title ->
                    val tags = title.child("TAGS").value
                    tags?.let {
                        it.toString().splitHash().forEach { tag ->
                            result.add(HashTag(content = tag))
                        }
                    }
                }
            }
        }
        return result.countElement().sortDescendingByCountOfElement()
    }

    private fun String.splitHash(): List<String> = split(" ", "\n", "#").filter { it.isNotBlank() }

    private fun List<*>.countElement(): Map<*, Int> = groupingBy { it }.eachCount()

    private fun Map<*, Int>.sortDescendingByCountOfElement(): List<HashTag> =
        toList().sortedBy { it.second }.toSet().reversed().map { (it.first as HashTag).copy(count = it.second) }

    init {
        getPopularTags()
    }
}