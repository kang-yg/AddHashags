package com.tagplus.addhashtags.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.TagPlusApplication
import com.tagplus.addhashtags.databinding.FragmentPopBinding
import com.tagplus.addhashtags.viewmodel.FragmentPopTagViewModel

class FragmentPopTags : Fragment() {
    private lateinit var fragmentPopBinding: FragmentPopBinding
    private lateinit var fragmentPopTagViewModel: FragmentPopTagViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pop, container, false)
        fragmentPopTagViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FragmentPopTagViewModel::class.java)
        return fragmentPopBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentPopTagViewModel.readDataFromFirebaseRealtimeData()
    }
}