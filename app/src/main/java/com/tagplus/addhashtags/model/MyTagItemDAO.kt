package com.tagplus.addhashtags.model

import androidx.room.*

@Dao
interface MyTagItemDAO {
    @Query("SELECT * FROM MyTagItem")
    fun gatAllItems(): List<MyTagItem>

    @Query("SELECT item_title FROM MyTagItem")
    fun getALLItemTitle(): String

    @Query("SELECT item_tags FROM MyTagItem")
    fun getALLItemTags(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyTagItem(myTagItem: MyTagItem)

    @Delete
    fun deleteItem(myTagItem: MyTagItem)
}