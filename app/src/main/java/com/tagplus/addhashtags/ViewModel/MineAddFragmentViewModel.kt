package com.tagplus.addhashtags.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.Model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MineAddFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun insertMineHashTagToDB(mineHashTag: MineHashTag) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertMineHashTagToDB(mineHashTag)
            }
        }
    }
}