package com.tagplus.addhashtags.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.Model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MineFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val allHashTagsLiveDataFromDB: LiveData<List<MineHashTag>> = repository.getAllMineHashTagsFromDB().asLiveData()

    fun updateMineHashTagToDB(mineHashTag: MineHashTag) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                repository.updateMineHashTagToDB(mineHashTag)
            }
        }
    }

    fun deleteMineHashTagFromDB(mineHashTag: MineHashTag) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteMineHashTagFromDB(mineHashTag)
            }
        }
    }
}