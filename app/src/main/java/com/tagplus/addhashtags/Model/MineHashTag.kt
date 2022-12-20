package com.tagplus.addhashtags.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "MyTagItem")
data class MineHashTag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_no") val no: Int = 0,
    @ColumnInfo(name = "item_title") val title: String,
    @ColumnInfo(name = "item_tags") val content: List<String>,
    @ColumnInfo(name = "favorite") val favorite: Boolean
) {
    @Ignore
    var expand: Boolean = false
}
