package com.tagplus.addhashtags.Model

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalServiceHelperImpl @Inject constructor(private val appDataBase: AppDataBase) : LocalServiceHelper {
    override fun getAllMineHashTags(): Flow<List<MineHashTag>> = appDataBase.localService().getAllMineHashTags()

    override fun getFavoriteMineHashTags(): Flow<List<MineHashTag>> = appDataBase.localService().getFavoriteMineHashTags()

    override fun insertMineHashTag(mineHashTag: MineHashTag) = appDataBase.localService().insertMineHashTag(mineHashTag)

    override fun updateMineHashTag(mineHashTag: MineHashTag) = appDataBase.localService().updateMineHashTag(mineHashTag)

    override fun deleteMineHashTag(mineHashTag: MineHashTag) = appDataBase.localService().deleteMineHashTag(mineHashTag)
}