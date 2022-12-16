package com.tagplus.addhashtags.Model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MineHashTag::class], version = 2)
@TypeConverters(MineHashTagTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun localService(): LocalService
}