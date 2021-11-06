package com.tagplus.addhashtags.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTagItem")
data class MyTagItem(
        @PrimaryKey
        @ColumnInfo(name = "item_title")
        var item_title: String,

        @ColumnInfo(name = "item_tags")
        var item_tags: String
)