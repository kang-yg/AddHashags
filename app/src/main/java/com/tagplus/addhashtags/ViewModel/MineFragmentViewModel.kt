package com.tagplus.addhashtags.ViewModel

import androidx.lifecycle.*
import com.tagplus.addhashtags.Model.MineHashTag
import com.tagplus.addhashtags.Model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MineFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _btShowFragmentMineSwitchAllAndFavoriteStateLiveData: MutableLiveData<Boolean> = MutableLiveData(true)
    val btShowFragmentMineSwitchAllAndFavoriteStateLiveData: LiveData<Boolean>
        get() = _btShowFragmentMineSwitchAllAndFavoriteStateLiveData
    val allHashTagsLiveDataFromDB: LiveData<List<MineHashTag>> = repository.getAllMineHashTagsFromDB().asLiveData()
    val favoriteHashTagsLiveDataFromDB: LiveData<List<MineHashTag>> = repository.getFavoriteMineHashTagsFromDB().asLiveData()

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

    fun switchAllAndFavoriteLiveDataState(isAll: Boolean) = _btShowFragmentMineSwitchAllAndFavoriteStateLiveData.postValue(isAll)
}