package com.tagplus.addhashtags.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentPopBinding

class FragmentPopTags : Fragment() {
    lateinit var fragmentPopBinding: FragmentPopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentPopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pop, container, false)



        return fragmentPopBinding.root
    }
}