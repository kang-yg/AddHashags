package com.tagplus.addhashtags

import android.app.Application
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TagPlusApplication : Application() {
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
            if (BuildConfig.DEBUG) {
                Log.d("FCMToken", "$fcmToken")
            }
        }
    }

    companion object {
        const val TAGS = "TAGS"
        const val USER_ID = "USER_ID"
        const val MAX_COPY_SIZE = 30
        val firebaseDatabase by lazy {
            Firebase.database
        }
        private val firebaseMessaging by lazy {
            FirebaseMessaging.getInstance()
        }
        var fcmToken: String? = null
    }
}