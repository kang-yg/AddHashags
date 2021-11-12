package com.tagplus.addhashtags.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.TagPlusApplication
import com.tagplus.addhashtags.databinding.FragmentPopBinding
import com.tagplus.addhashtags.viewmodel.FragmentPopTagViewModel

class FragmentPopTags : Fragment() {
    private lateinit var fragmentPopBinding: FragmentPopBinding
    private lateinit var fragmentPopTagViewModel: FragmentPopTagViewModel
    private val popTagsChipGroup by lazy {
        fragmentPopBinding.popTagsChipGroup
    }
    private val popTagsProgressIndicator by lazy {
        fragmentPopBinding.popTagsProgressIndicator
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pop, container, false)
        fragmentPopTagViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FragmentPopTagViewModel::class.java)
        return fragmentPopBinding.root
    }

    override fun onStart() {
        super.onStart()
        fragmentPopTagViewModel.readDataFromFirebaseRealtimeData(setPopTagsChipGroup())
    }

    private fun setPopTagsChipGroup(): () -> Unit = {
        var viewCount = 0
        popTagsProgressIndicator.visibility = View.VISIBLE
        popTagsChipGroup.removeAllViews()

        fragmentPopTagViewModel.tagSortedCountMap.forEach { map ->
            if (viewCount < TagPlusApplication.MAX_COPY_SIZE) {
                popTagsChipGroup.addView(Chip(context).also { chip ->
                    viewCount++
                    chip.text = map.key
                })
            }
        }
        popTagsProgressIndicator.visibility = View.GONE
        fragmentPopTagViewModel.tagSortedCountMap = mapOf()
    }
}