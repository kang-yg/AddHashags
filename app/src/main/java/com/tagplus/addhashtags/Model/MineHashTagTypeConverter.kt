package com.tagplus.addhashtags.Model

import androidx.room.TypeConverter
import com.google.gson.Gson

class MineHashTagTypeConverter {
    @TypeConverter
    fun listToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<String> = Gson().fromJson(value, Array<String>::class.java).toList()
}