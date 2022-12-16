package com.tagplus.addhashtags.Model

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class Repository @Inject constructor(private val databaseReference: DatabaseReference, private val localServiceHelper: LocalServiceHelper) {
    fun getDatabaseReference() = databaseReference

    fun getAllMineHashTagsFromDB() = localServiceHelper.getAllMineHashTags()

    fun insertMineHashTagToDB(mineHashTag: MineHashTag) = localServiceHelper.insertMineHashTag(mineHashTag)
}