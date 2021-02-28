package com.tagplus.addhashtags.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MyTagItemDAO {
    @Query("SELECT * FROM MyTagItem")
    fun gatAllItems(): Single<List<MyTagItem>>

    @Query("SELECT item_title FROM MyTagItem")
    fun getALLItemTitle(): String

    @Query("SELECT item_tags FROM MyTagItem")
    fun getALLItemTags(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyTagItem(myTagItem: MyTagItem): Completable
}