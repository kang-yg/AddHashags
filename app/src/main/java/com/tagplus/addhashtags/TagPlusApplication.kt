package com.tagplus.addhashtags

import android.app.Application
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class TagPlusApplication : Application() {
    companion object {
        const val TITLE = "TITLE"
        const val TAGS = "TAGS"
        val firebaseDatabase by lazy {
            Firebase.database
        }
        private val firebaseMessaging by lazy {
            FirebaseMessaging.getInstance()
        }
        var fcmToken: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        getFCMToken()
    }

    // Get FCM token
    private fun getFCMToken() {
        firebaseMessaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {

                return@addOnCompleteListener
            }

            fcmToken = task.result
        }
    }
}