package com.tagplus.addhashtags.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.tabs.TabLayoutMediator
import com.tagplus.addhashtags.AppDatabase
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.ActivityMainBinding
import com.tagplus.addhashtags.view.adapter.ViewPagerAdapter
import com.tagplus.addhashtags.view.viewmodelfactory.ActivityMainViewModelFactory
import com.tagplus.addhashtags.view.viewmodelfactory.FragmentMineTagListViewModelFactory
import com.tagplus.addhashtags.viewmodel.ActivityMainViewModel

class ActivityMain : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var activityMainViewModel: ActivityMainViewModel

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(this, AppDatabase::class.java, "AddHashTags").build()
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainViewPager2.adapter = ViewPagerAdapter(this)
        activityMainViewModel = ViewModelProvider(this, ActivityMainViewModelFactory(db)).get(ActivityMainViewModel::class.java)

        var tabList = arrayListOf<String>()
        tabList.add("POP")
        tabList.add("MINE")

        TabLayoutMediator(activityMainBinding.mainTabLayout, activityMainBinding.mainViewPager2) { tablayout, viewpager2 ->
            tablayout.text = tabList[viewpager2]
        }.attach()
    }
}