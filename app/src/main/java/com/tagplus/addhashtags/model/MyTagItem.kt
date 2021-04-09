package com.tagplus.addhashtags.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.databinding.library.baseAdapters.BR

@Entity(tableName = "MyTagItem")
data class MyTagItem(
        @PrimaryKey
        @ColumnInfo(name = "item_title")
        var item_title: String,

        @ColumnInfo(name = "item_tags")
        var item_tags: String
)