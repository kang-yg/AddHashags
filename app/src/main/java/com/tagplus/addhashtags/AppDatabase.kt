package com.tagplus.addhashtags

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tagplus.addhashtags.model.MyTagItem
import com.tagplus.addhashtags.model.MyTagItemDAO

@Database(entities = [MyTagItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myTagItemDAO(): MyTagItemDAO
}