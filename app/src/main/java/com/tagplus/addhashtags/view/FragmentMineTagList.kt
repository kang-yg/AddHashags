package com.tagplus.addhashtags.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineTaglistBinding
import com.tagplus.addhashtags.view.adapter.MyTagListAdapter
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineTagListViewModelFactory
import com.tagplus.addhashtags.viewmodel.FragmentMineTagListViewModel

class FragmentMineTagList : Fragment() {

    private lateinit var fragmentMineTagListBinding: FragmentMineTaglistBinding
    private lateinit var fragmentMineTagListViewModel: FragmentMineTagListViewModel

    lateinit var db: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "AddHashTags").build()

        fragmentMineTagListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_taglist, container, false)
        fragmentMineTagListBinding.fragmentMineTagList = this
        fragmentMineTagListViewModel = ViewModelProvider(this, FragmentMineTagListViewModelFactory(db)).get(FragmentMineTagListViewModel::class.java)
        fragmentMineTagListViewModel.testArray.observe(this, Observer { setRecyclerView() })

        return fragmentMineTagListBinding.root
    }

    private fun setRecyclerView() {
        val mineTagListRecyclerView = fragmentMineTagListBinding.mineRecyclerView
        val myTagListAdapter = MyTagListAdapter()
        mineTagListRecyclerView.layoutManager = LinearLayoutManager(context)
        mineTagListRecyclerView.adapter = myTagListAdapter
        myTagListAdapter.tagItemList = fragmentMineTagListViewModel.testArray
    }

    // TODO ReactiveX 이용 더블클릭 방지
    fun floatingButtonClick() {
        val mineTagListFrame = fragmentMineTagListBinding.mineTagListFrame
        val fragmentManager = requireFragmentManager().beginTransaction()
        fragmentManager.replace(mineTagListFrame.id, FragmentMineAddTag()).commitAllowingStateLoss()
        fragmentManager.addToBackStack(null)
    }
}