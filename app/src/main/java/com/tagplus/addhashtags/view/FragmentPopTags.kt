package com.tagplus.addhashtags.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentPopBinding

class FragmentPopTags : Fragment() {
    private lateinit var fragmentPopBinding: FragmentPopBinding

    private val firebaseDatabase by lazy {
        Firebase.database
    }
    private val firebaseMessaging by lazy {
        FirebaseMessaging.getInstance()
    }

    private var fcmToken: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pop, container, false)

        return fragmentPopBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFCMToken()
        readDataFromFirebaseRealtimeData()
    }

    override fun onResume() {
        super.onResume()

        writeDataToFirebaseRealtimeData()
    }

    // Firebase write
    private fun writeDataToFirebaseRealtimeData() {
        fcmToken?.let { firebaseDatabase.getReference(it).child(TITLE).setValue("title") }
        fcmToken?.let { firebaseDatabase.getReference(it).child(TAG).setValue("tag") }
    }

    // Firebase read
    private fun readDataFromFirebaseRealtimeData() {
        firebaseDatabase.getReference("message").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                Log.d("AAA", value.toString())
            }

            // Failed to read value
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    // Sort title

    // Sort tags

    // Get FCM token
    private fun getFCMToken() {
        firebaseMessaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {

                return@addOnCompleteListener
            }

            fcmToken = task.result
            Log.d("AAA", fcmToken ?: "null")
        }
    }

    companion object {
        const val TITLE = "TITLE"
        const val TAG = "TAG"
    }
}