package com.tagplus.addhashtags.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.ActivityMainBinding
import com.tagplus.addhashtags.view.adapter.ViewPagerAdapter

class ActivityMain : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var tabList = arrayListOf<String>()
        tabList.add("POP")
        tabList.add("MINE")

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainViewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(activityMainBinding.mainTabLayout, activityMainBinding.mainViewPager2) { tablayout, viewpager2 ->
            tablayout.text = tabList[viewpager2]
        }.attach()
    }
}