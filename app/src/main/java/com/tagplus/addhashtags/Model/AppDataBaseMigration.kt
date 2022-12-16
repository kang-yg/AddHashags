package com.tagplus.addhashtags.Model

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.also {
            it.execSQL("ALTER TABLE MyTagItem ADD COLUMN item_no INTEGER")
            it.execSQL("ALTER TABLE MyTagItem ADD COLUMN favorite BOOLEAN")
        }
    }
}