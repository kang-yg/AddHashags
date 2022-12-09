package com.tagplus.addhashtags.Model

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class Repository @Inject constructor(private val databaseReference: DatabaseReference) {
    fun getDatabaseReference() = databaseReference
}