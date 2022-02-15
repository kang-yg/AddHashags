package com.tagplus.addhashtags.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.tabs.TabLayoutMediator
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.ActivityMainBinding
import com.tagplus.addhashtags.view.adapter.ViewPagerAdapter
import com.tagplus.addhashtags.viewmodel.ActivityMainViewModel
import com.tagplus.addhashtags.viewmodel.viewmodelfactory.ActivityMainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityMain : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private lateinit var activityMainViewModel: ActivityMainViewModel

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "AddHashTags").build()
    }

    @Inject
    lateinit var fragmentPopTags: FragmentPopTags

    @Inject
    lateinit var fragmentMine: FragmentMine

    @Inject
    lateinit var fragmentMineTagList: FragmentMineTagList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        activityMainViewModel = ViewModelProvider(this, ActivityMainViewModelFactory(db)).get(ActivityMainViewModel::class.java)

        initMainViewPager()
        initMainTapLayout()
    }

    private fun initMainViewPager() {
        activityMainBinding.mainViewPager2.adapter = ViewPagerAdapter(this, fragmentPopTags, fragmentMine)
    }

    private fun initMainTapLayout() {
        val tabList = arrayListOf<String>()
        tabList.add(getString(R.string.popularTags))
        tabList.add(getString(R.string.myTags))

        TabLayoutMediator(activityMainBinding.mainTabLayout, activityMainBinding.mainViewPager2) { tablayout, viewpager2 ->
            tablayout.text = tabList[viewpager2]
        }.attach()
    }

    fun refreshFragmentMineTagList() {
        fragmentMineTagList.showTagList()
    }
}