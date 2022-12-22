package com.tagplus.addhashtags.Model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalService {
    @Query("SELECT * FROM MyTagItem")
    fun getAllMineHashTags(): Flow<List<MineHashTag>>

    @Query("SELECT * FROM MyTagItem WHERE favorite = 1")
    fun getFavoriteMineHashTags(): Flow<List<MineHashTag>>

    @Insert
    fun insertMineHashTag(mineHashTag: MineHashTag)

    @Update
    fun updateMineHashTag(mineHashTag: MineHashTag)

    @Delete
    fun deleteMineHashTag(mineHashTag: MineHashTag)
}