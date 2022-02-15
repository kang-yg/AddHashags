package com.tagplus.addhashtags.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentMine : Fragment() {
    lateinit var fragmentMineBinding: FragmentMineBinding
    private val mineFrame by lazy {
        fragmentMineBinding.mineFrame
    }

    @Inject
    lateinit var fragmentMineAddTag: FragmentMineAddTag

    @Inject
    lateinit var fragmentMineTagList: FragmentMineTagList

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMineBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)

        return fragmentMineBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (!fragmentMineAddTag.isAdded) {
            activity?.supportFragmentManager?.beginTransaction()?.replace(mineFrame.id, fragmentMineTagList)?.commit()
        }
        else {
            activity?.supportFragmentManager?.beginTransaction()?.show(fragmentMineAddTag)?.commit()
        }
    }
}