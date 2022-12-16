package com.tagplus.addhashtags.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalService {
    @Query("SELECT * FROM MyTagItem")
    fun getAllMineHashTags(): Flow<List<MineHashTag>>

    @Insert
    fun insertMineHashTag(mineHashTag: MineHashTag)

    @Delete
    fun deleteMineHashTag(mineHashTag: MineHashTag)
}