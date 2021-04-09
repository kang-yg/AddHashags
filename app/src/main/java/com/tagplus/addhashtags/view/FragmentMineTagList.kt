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
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.FragmentMineTaglistBinding
import com.tagplus.addhashtags.view.adapter.MyTagListAdapter
import com.tagplus.addhashtags.view.viewmodelfactory.ActivityMainViewModelFactory
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineTagListViewModelFactory
import com.tagplus.addhashtags.viewmodel.ActivityMainViewModel
import com.tagplus.addhashtags.viewmodel.FragmentMineTagListViewModel

class FragmentMineTagList : Fragment() {

    private lateinit var fragmentMineTagListBinding: FragmentMineTaglistBinding
    private lateinit var fragmentMineTagListViewModel: FragmentMineTagListViewModel
    private lateinit var activityMainViewModel: ActivityMainViewModel

    lateinit var db: AppDatabase
    lateinit var mineTagListRecyclerView: RecyclerView
    lateinit var myTagListAdapter: MyTagListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        db = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "AddHashTags").build()

        fragmentMineTagListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_taglist, container, false)
        fragmentMineTagListBinding.fragmentMineTagList = this
        mineTagListRecyclerView = fragmentMineTagListBinding.mineRecyclerView
        myTagListAdapter = MyTagListAdapter()
        fragmentMineTagListViewModel = ViewModelProvider(this, FragmentMineTagListViewModelFactory(db)).get(FragmentMineTagListViewModel::class.java)
        activityMainViewModel = ViewModelProvider(this, ActivityMainViewModelFactory(db)).get(ActivityMainViewModel::class.java)
        setRecyclerView()

        activityMainViewModel.testArray.observe(this, Observer { myTagListAdapter.notifyDataSetChanged() })

        return fragmentMineTagListBinding.root
    }

    private fun setRecyclerView() {
        mineTagListRecyclerView.layoutManager = LinearLayoutManager(context)
        mineTagListRecyclerView.adapter = myTagListAdapter
        myTagListAdapter.tagItemList = activityMainViewModel.testArray
    }

    // TODO ReactiveX 이용 더블클릭 방지
    fun floatingButtonClick() {
        val mineTagListFrame = fragmentMineTagListBinding.mineTagListFrame
        val fragmentManager = requireFragmentManager().beginTransaction()
        fragmentManager.replace(mineTagListFrame.id, FragmentMineAddTag()).commitAllowingStateLoss()
        fragmentManager.addToBackStack(null)
    }
}