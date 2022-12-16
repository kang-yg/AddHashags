package com.tagplus.addhashtags.Model

import kotlinx.coroutines.flow.Flow

interface LocalServiceHelper {
    fun getAllMineHashTags(): Flow<List<MineHashTag>>
    fun insertMineHashTag(mineHashTag: MineHashTag)
    fun deleteMineHashTag(mineHashTag: MineHashTag)
}