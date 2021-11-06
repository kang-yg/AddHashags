package com.tagplus.addhashtags.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineBinding

class FragmentMine : Fragment() {
    lateinit var fragmentMineBinding: FragmentMineBinding
    private val mineFrame by lazy {
        fragmentMineBinding.mineFrame
    }
    private val fragmentManager by lazy {
        requireFragmentManager().beginTransaction()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)

        return fragmentMineBinding.root
    }

    override fun onStart() {
        super.onStart()
        fragmentManager.replace(mineFrame.id, FragmentMineTagList()).commit()
    }
}