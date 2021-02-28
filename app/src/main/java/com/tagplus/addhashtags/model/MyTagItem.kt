package com.tagplus.addhashtags.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTagItem")
data class MyTagItem(
        @PrimaryKey
        @ColumnInfo(name = "item_title")
        var itemTitle: String,
        @ColumnInfo(name = "item_tags")
        var itemTags: String
)