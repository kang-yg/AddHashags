package com.tagplus.addhashtags.View

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.tagplus.addhashtags.R
import com.tagplus.addhashtags.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.let {
            navController = findNavController(R.id.mainNavHost)

            initMainTabLayout(it)
        }
    }

    private fun initMainTabLayout(activityMainBinding: ActivityMainBinding) {
        activityMainBinding.mainTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.text) {
                    getString(R.string.popularTags) -> {
                        navController.navigate(R.id.action_fragmentMine_to_fragmentPop)
                    }

                    getString(R.string.myTags) -> {
                        navController.navigate(R.id.action_fragmentPop_to_fragmentMine)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}