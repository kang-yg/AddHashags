package com.tagplus.addhashtags.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.Model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MineFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val allHashTagsLiveDataFromDB: LiveData<List<MineHashTag>> = repository.getAllMineHashTagsFromDB().asLiveData()
}